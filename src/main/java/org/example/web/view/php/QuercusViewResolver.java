package org.example.web.view.php;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

/**
 * Convenience subclass of {@link org.springframework.web.servlet.view.UrlBasedViewResolver}
 * that supports {@link QuercusView} for rendering PHP scripts.
 */
public class QuercusViewResolver extends AbstractTemplateViewResolver
{
  /**
   * Sets {@link QuercusView} as the view to use for rendering scripts.
   */
  public QuercusViewResolver()
  {
    setViewClass(requiredViewClass());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Class<?> requiredViewClass()
  {
    return QuercusView.class;
  }
}
