package org.example.web.view.php;

import com.caucho.quercus.*;
import com.caucho.quercus.env.Env;
import com.caucho.quercus.env.QuercusValueException;
import com.caucho.quercus.env.StringValue;
import com.caucho.quercus.page.QuercusPage;
import com.caucho.quercus.servlet.api.QuercusHttpServletRequestImpl;
import com.caucho.quercus.servlet.api.QuercusHttpServletResponseImpl;
import com.caucho.quercus.servlet.api.QuercusServletContextImpl;
import com.caucho.util.L10N;
import com.caucho.vfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractTemplateView;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * View using the Quercus PHP processing engine.
 */
public class QuercusView extends AbstractTemplateView
{
  private static final L10N   LOCALIZATION              = new L10N(QuercusView.class);
  private static final Logger LOGGER                    = LoggerFactory.getLogger(QuercusView.class);
  private static final String PATH_ENVIRONMENT_VARIABLE = "user.dir";
  private static final String ROOT_CONTEXT              = "/";

  protected QuercusContext quercusContext;
  protected ServletContext servletContext;

  /**
   * Default constructor.
   */
  public QuercusView()
  {
    super();
  }

  /**
   * {@inheritDoc}
   */
  protected void initServletContext(final ServletContext servletContext)
  {
    this.servletContext = servletContext;

    checkServletAPIVersion();

    getQuercus().setPwd(new FilePath(this.servletContext.getRealPath(ROOT_CONTEXT)));

    getQuercus().init();
  }

  /**
   * {@inheritDoc}
   */
  protected void renderMergedTemplateModel(final Map model
      , final HttpServletRequest request
      , final HttpServletResponse response)
      throws Exception
  {
    Env env = null;
    WriteStream ws = null;

    try
    {
      final Path path = getPath(request);

      QuercusPage page;

      try
      {
        page = getQuercus().parse(path);
      }
      catch (final FileNotFoundException ex)
      {
        LOGGER.error(ex.toString(), ex);

        response.sendError(HttpServletResponse.SC_NOT_FOUND);

        return;
      }

      StreamImpl out;

      try
      {
        out = new VfsStream(null, response.getOutputStream());
      }
      catch (final IllegalStateException e)
      {
        final WriterStreamImpl writer = new WriterStreamImpl();
        writer.setWriter(response.getWriter());

        out = writer;
      }

      ws = new WriteStream(out);

      ws.setNewlineString("\n");

      final QuercusContext quercus = getQuercus();
      quercus.setServletContext(new QuercusServletContextImpl(servletContext));

      env = quercus.createEnv(page
          , ws
          , new QuercusHttpServletRequestImpl(request)
          , new QuercusHttpServletResponseImpl(response));

      // retro... thanks, Spring
      for (Object entryObj : model.entrySet())
      {
        Map.Entry entry = (Map.Entry) entryObj;
        env.setScriptGlobal((String) entry.getKey(), entry.getValue());
      }

      try
      {
        env.start();

        env.setScriptGlobal("request", request);
        env.setScriptGlobal("response", response);
        env.setScriptGlobal("servletContext", servletContext);

        StringValue prepend = quercus.getIniValue("auto_prepend_file").toStringValue(env);
        if (prepend.length() > 0)
        {
          Path prependPath = env.lookup(prepend);

          if (prependPath == null)
          {
            env.error(LOCALIZATION.l("auto_prepend_file '{0}' not found.", prepend));
          }
          else
          {
            QuercusPage prependPage = getQuercus().parse(prependPath);
            prependPage.executeTop(env);
          }
        }

        env.executeTop();

        StringValue append = quercus.getIniValue("auto_append_file").toStringValue(env);
        if (append.length() > 0)
        {
          Path appendPath = env.lookup(append);

          if (appendPath == null)
          {
            env.error(LOCALIZATION.l("auto_append_file '{0}' not found.", append));
          }
          else
          {
            QuercusPage appendPage = getQuercus().parse(appendPath);
            appendPage.executeTop(env);
          }
        }
      }
      catch (final QuercusExitException | QuercusErrorException e)
      {
        throw e;
      }
      catch (final QuercusLineRuntimeException e)
      {
        LOGGER.debug(e.toString(), e);
      }
      catch (final QuercusValueException e)
      {
        LOGGER.info(e.toString(), e);

        ws.println(e.toString());
      }
      catch (final Throwable e)
      {
        if (response.isCommitted())
        {
          e.printStackTrace(ws.getPrintWriter());
        }

        ws = null;

        throw e;
      }
      finally
      {
        if (env != null)
        {
          env.close();
        }

        if (ws != null)
        {
          ws.close();
        }
      }
    }
    catch (final QuercusDieException e)
    {
      LOGGER.info(e.toString(), e);
    }
    catch (final QuercusExitException | QuercusErrorException e)
    {
      LOGGER.debug(e.toString(), e);
    }
    catch (final RuntimeException e)
    {
      throw e;
    }
    catch (final Throwable e)
    {
      throw new ServletException(e);
    }
  }

  /**
   * Checks to ensure that the current Servlet environment supports
   * Servlet API version 2.4 or higher.
   *
   * @throws QuercusRuntimeException if the current Servlet environment
   *                                 does not support at least Servlet
   *                                 API version 2.4.
   */
  private void checkServletAPIVersion()
  {
    int major = servletContext.getMajorVersion();
    int minor = servletContext.getMinorVersion();

    if (major < 2 || major == 2 && minor < 4)
    {
      throw new QuercusRuntimeException(LOCALIZATION.l("Quercus requires Servlet API 2.4+."));
    }
  }

  /**
   * Gets the local filesystem path for an HTTP request.
   *
   * @param request The request for which the path needs to be determined.
   * @return The path for the request.
   */
  private Path getPath(final HttpServletRequest request)
  {
    final String scriptPath = getUrl();

    final Path currentPath = new FilePath(System.getProperty(PATH_ENVIRONMENT_VARIABLE));

    final Path path = currentPath.lookup(servletContext.getRealPath(scriptPath));

    if (path.isFile())
    {
      return path;
    }

    final String pathInfo = QuercusRequestAdapter.getPagePathInfo(new QuercusHttpServletRequestImpl(request));
    final String fullPath = pathInfo != null
        ? scriptPath + pathInfo
        : scriptPath;

    return currentPath.lookup(servletContext.getRealPath(fullPath));
  }

  /**
   * Gets a {@link QuercusContext} for running PHP scripts.
   *
   * @return A {@link QuercusContext}.
   */
  private QuercusContext getQuercus()
  {
    synchronized (this)
    {
      if (quercusContext == null)
      {
        quercusContext = new QuercusContext();

        quercusContext.start();
      }
    }

    return quercusContext;
  }
}
