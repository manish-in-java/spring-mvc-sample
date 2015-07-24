package org.example.web.controller;

import org.example.model.Day;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Integration tests for {@link EnumController}.
 */
public class EnumControllerTest extends RESTControllerTest
{
  @Autowired
  private EnumController controller;

  /**
   * Tests that the home page can be loaded successfully.
   */
  @Test
  public void testHome() throws Exception
  {
    mock.perform(get(EnumController.PATH_HOME))
        .andExpect(status().isOk())
        .andExpect(view().name(EnumController.VIEW_HOME));
  }

  /**
   * Tests that a choice can be sent to the server by HTTP POST.
   */
  @Test
  public void testModel() throws Exception
  {
    mock.perform(post(EnumController.PATH_MODEL).param(EnumController.PARAM_DAY, Day.THURSDAY.name()))
        .andExpect(status().isOk())
        .andExpect(view().name(EnumController.VIEW_MODEL));
  }

  /**
   * Tests that a choice can be sent to the server by HTTP POST.
   */
  @Test
  public void testParam() throws Exception
  {
    mock.perform(post(EnumController.PATH_PARAM).param(EnumController.PARAM_DAY, Day.MONDAY.name()))
        .andExpect(status().isOk())
        .andExpect(view().name(EnumController.VIEW_PARAM));
  }

  /**
   * {@inheritDoc}
   */
  protected Object controller()
  {
    return controller;
  }
}
