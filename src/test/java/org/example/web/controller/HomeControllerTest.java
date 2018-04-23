package org.example.web.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Integration tests for {@link HomeController}.
 */
public class HomeControllerTest extends ControllerTest
{
  @Autowired
  private HomeController controller;

  /**
   * Tests that the home page can be loaded successfully.
   */
  @Test
  public void testHome() throws Exception
  {
    mock.perform(get(HomeController.PATH_HOME))
        .andExpect(status().isOk())
        .andExpect(view().name(HomeController.VIEW_HOME));
  }

  /**
   * {@inheritDoc}
   */
  protected Object controller()
  {
    return controller;
  }
}
