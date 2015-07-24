package org.example.web.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for {@link HttpPostController}.
 */
public class HttpPostControllerTest extends ControllerTest
{
  @Autowired
  private HttpPostController controller;

  /**
   * Tests that data can be posted successfully to a REST API endpoint.
   */
  @Test
  public void testPost() throws Exception
  {
    mock.perform(post(HttpPostController.PATH_POST)
                     .accept(MediaType.APPLICATION_JSON)
                     .contentType(MediaType.APPLICATION_JSON)
                     .param(HttpPostController.PARAM_NAME_FIRST, "John")
                     .param(HttpPostController.PARAM_NAME_LAST, "Doe"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  /**
   * {@inheritDoc}
   */
  protected Object controller()
  {
    return controller;
  }
}
