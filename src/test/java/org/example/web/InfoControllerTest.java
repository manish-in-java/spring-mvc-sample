package org.example.web;

import org.example.web.converter.ProtocolBufferHttpMessageConverter;
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
    mock.perform(get("/info.json")
                     .accept(MediaType.APPLICATION_JSON)
                     .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  /**
   * Tests that Google Protocol Buffer output can be retrieved successfully from a REST API endpoint.
   */
  @Test
  public void testInfoAsProtocolBuffer() throws Exception
  {
    mock.perform(get("/info.proto")
                     .accept(ProtocolBufferHttpMessageConverter.MEDIA_TYPE)
                     .contentType(ProtocolBufferHttpMessageConverter.MEDIA_TYPE))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(ProtocolBufferHttpMessageConverter.MEDIA_TYPE));
  }

  /**
   * Tests that XML output can be retrieved successfully from a REST API endpoint.
   */
  @Test
  public void testInfoAsXML() throws Exception
  {
    mock.perform(get("/info.xml")
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
