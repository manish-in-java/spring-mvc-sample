package org.example.web.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Integration tests for {@link TemplateController}.
 */
public class TemplateControllerTest extends ControllerTest
{
  @Autowired
  private TemplateController controller;

  /**
   * Tests that a PHP-generated page can be loaded successfully.
   */
  @Test
  public void testPhp() throws Exception
  {
    mock.perform(get(TemplateController.PATH_PHP))
        .andExpect(status().isOk())
        .andExpect(view().name(TemplateController.VIEW_PHP));
  }

  /**
   * {@inheritDoc}
   */
  protected Object controller()
  {
    return controller;
  }
}
