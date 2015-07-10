package org.example.web.controller;

import org.example.model.Day;
import org.example.web.controller.EnumController;
import org.example.web.controller.RESTControllerTest;
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
    mock.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("home"));
  }

  /**
   * Tests that a choice can be sent to the server by HTTP POST.
   */
  @Test
  public void testModel() throws Exception
  {
    mock.perform(post("/model").param("day", Day.THURSDAY.name()))
        .andExpect(status().isOk())
        .andExpect(view().name("model"));
  }

  /**
   * Tests that a choice can be sent to the server by HTTP POST.
   */
  @Test
  public void testParam() throws Exception
  {
    mock.perform(post("/param").param("day", Day.MONDAY.name()))
        .andExpect(status().isOk())
        .andExpect(view().name("param"));
  }

  /**
   * {@inheritDoc}
   */
  protected Object controller()
  {
    return controller;
  }
}
