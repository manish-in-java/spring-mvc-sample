package org.example.web.controller;

import org.example.web.converter.ProtocolBufferHttpMessageConverter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for {@link InfoController}.
 */
public class InfoControllerTest extends RESTControllerTest
{
  @Autowired
  private InfoController controller;

  /**
   * Tests that JSON output can be retrieved successfully from a REST API endpoint.
   */
  @Test
  public void testApiAsJSON() throws Exception
  {
    mock.perform(get(InfoController.PATH_JSON)
                     .accept(MediaType.APPLICATION_JSON)
                     .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  /**
   * Tests that Google Protocol Buffer output can be retrieved successfully from a REST API endpoint.
   */
  @Test
  public void testApiAsProtocolBuffer() throws Exception
  {
    mock.perform(get(InfoController.PATH_PROTO)
                     .accept(ProtocolBufferHttpMessageConverter.MEDIA_TYPE)
                     .contentType(ProtocolBufferHttpMessageConverter.MEDIA_TYPE))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(ProtocolBufferHttpMessageConverter.MEDIA_TYPE));
  }

  /**
   * Tests that XML output can be retrieved successfully from a REST API endpoint.
   */
  @Test
  public void testApiAsXML() throws Exception
  {
    mock.perform(get(InfoController.PATH_XML)
                     .accept(MediaType.APPLICATION_XML)
                     .contentType(MediaType.APPLICATION_XML))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML));
  }

  /**
   * Tests that the informational page can be loaded successfully.
   */
  @Test
  public void testPage() throws Exception
  {
    mock.perform(get(InfoController.PATH_PAGE))
        .andExpect(status().isOk())
        .andExpect(view().name(InfoController.VIEW_PAGE));
  }

  /**
   * {@inheritDoc}
   */
  protected Object controller()
  {
    return controller;
  }
}
