package org.example.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for {@link InfoController}.
 */
public class InfoControllerTest extends ControllerTest
{
  @Autowired
  private InfoController controller;

  /**
   * Tests that JSON output can be retrieved successfully from a REST API endpoint.
   */
  @Test
  public void testInfoAsJSON() throws Exception
  {
    mock.perform(get("/info")
                     .accept(MediaType.APPLICATION_JSON)
                     .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  /**
   * Tests that XML output can be retrieved successfully from a REST API endpoint.
   */
  @Test
  public void testInfoAsXML() throws Exception
  {
    mock.perform(get("/info")
                     .accept(MediaType.APPLICATION_XML)
                     .contentType(MediaType.APPLICATION_XML))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML));
  }

  /**
   * {@inheritDoc}
   */
  protected Object controller()
  {
    return controller;
  }
}
