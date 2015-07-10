package org.example.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Integration tests for a REST controller.
 */
public abstract class RESTControllerTest extends ControllerTest
{
  private static final ObjectMapper mapper = new ObjectMapper();

  /**
   * Serializes an object into a byte array.
   *
   * @param object The object to serialize.
   * @return The object serialized into a byte array.
   */
  protected byte[] serialize(final Object object) throws Exception
  {
    return object == null
        ? null
        : mapper.writeValueAsBytes(object);
  }
}
