package org.example.web.controller;

import org.example.model.Day;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Integration tests for {@link FormBindingController}.
 */
public class FormBindingControllerTest extends RESTControllerTest
{
  @Autowired
  private FormBindingController controller;

  /**
   * Tests that the form binding page can be loaded successfully.
   */
  @Test
  public void testForm() throws Exception
  {
    mock.perform(get(FormBindingController.PATH_FORM))
        .andExpect(status().isOk())
        .andExpect(view().name(FormBindingController.VIEW_FORM));
  }

  /**
   * Tests that a choice can be sent to the server by HTTP POST.
   */
  @Test
  public void testModel() throws Exception
  {
    mock.perform(post(FormBindingController.PATH_MODEL).param(FormBindingController.PARAM_DAY, Day.THURSDAY.name()))
        .andExpect(status().isOk())
        .andExpect(view().name(FormBindingController.VIEW_MODEL));
  }

  /**
   * Tests that a choice can be sent to the server by HTTP POST.
   */
  @Test
  public void testParam() throws Exception
  {
    mock.perform(post(FormBindingController.PATH_PARAM).param(FormBindingController.PARAM_DAY, Day.MONDAY.name()))
        .andExpect(status().isOk())
        .andExpect(view().name(FormBindingController.VIEW_PARAM));
  }

  /**
   * {@inheritDoc}
   */
  protected Object controller()
  {
    return controller;
  }
}
