package org.example.web.controller;

import org.example.web.converter.ProtocolBufferHttpMessageConverter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for {@link TemplateController}.
 */
public class TemplateControllerTest extends RESTControllerTest
{
  @Autowired
  private TemplateController controller;

  /**
   * Tests that a PHP-generated page can be loaded successfully.
   */
  @Test
  public void testPhp() throws Exception
  {
    mock.perform(get("/php"))
        .andExpect(status().isOk())
        .andExpect(view().name("php/template"));
  }

  /**
   * {@inheritDoc}
   */
  protected Object controller()
  {
    return controller;
  }
}
