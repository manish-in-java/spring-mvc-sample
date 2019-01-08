package org.example.web.converter;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.http.MockHttpOutputMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

/**
 * Unit tests for {@link ProtocolBufferHttpMessageConverter}.
 */
public class ProtocolBufferHttpMessageConverterTest
{
  private ProtocolBufferHttpMessageConverter converter;

  /**
   * Creates a converter to run the tests.
   */
  @Before
  public void setup()
  {
    converter = new ProtocolBufferHttpMessageConverter();
  }

  /**
   * Tests that the converter correctly detects media types it can read.
   */
  @Test
  public void testCanRead()
  {
    Assert.assertTrue(converter.canRead(getClass(), ProtocolBufferHttpMessageConverter.MEDIA_TYPE));

    Assert.assertFalse(converter.canRead(getClass(), MediaType.APPLICATION_ATOM_XML));
    Assert.assertFalse(converter.canRead(getClass(), MediaType.APPLICATION_FORM_URLENCODED));
    Assert.assertFalse(converter.canRead(getClass(), MediaType.APPLICATION_JSON));
    Assert.assertFalse(converter.canRead(getClass(), MediaType.APPLICATION_OCTET_STREAM));
    Assert.assertFalse(converter.canRead(getClass(), MediaType.APPLICATION_XHTML_XML));
    Assert.assertFalse(converter.canRead(getClass(), MediaType.APPLICATION_XML));
    Assert.assertFalse(converter.canRead(getClass(), MediaType.IMAGE_GIF));
    Assert.assertFalse(converter.canRead(getClass(), MediaType.IMAGE_JPEG));
    Assert.assertFalse(converter.canRead(getClass(), MediaType.IMAGE_PNG));
    Assert.assertFalse(converter.canRead(getClass(), MediaType.MULTIPART_FORM_DATA));
    Assert.assertFalse(converter.canRead(getClass(), MediaType.TEXT_HTML));
    Assert.assertFalse(converter.canRead(getClass(), MediaType.TEXT_PLAIN));
    Assert.assertFalse(converter.canRead(getClass(), MediaType.TEXT_XML));
  }

  /**
   * Tests that the converter correctly detects media types it can write.
   */
  @Test
  public void testCanWrite()
  {
    Assert.assertTrue(converter.canWrite(getClass(), ProtocolBufferHttpMessageConverter.MEDIA_TYPE));

    Assert.assertFalse(converter.canWrite(getClass(), MediaType.APPLICATION_ATOM_XML));
    Assert.assertFalse(converter.canWrite(getClass(), MediaType.APPLICATION_FORM_URLENCODED));
    Assert.assertFalse(converter.canWrite(getClass(), MediaType.APPLICATION_JSON));
    Assert.assertFalse(converter.canWrite(getClass(), MediaType.APPLICATION_OCTET_STREAM));
    Assert.assertFalse(converter.canWrite(getClass(), MediaType.APPLICATION_XHTML_XML));
    Assert.assertFalse(converter.canWrite(getClass(), MediaType.APPLICATION_XML));
    Assert.assertFalse(converter.canWrite(getClass(), MediaType.IMAGE_GIF));
    Assert.assertFalse(converter.canWrite(getClass(), MediaType.IMAGE_JPEG));
    Assert.assertFalse(converter.canWrite(getClass(), MediaType.IMAGE_PNG));
    Assert.assertFalse(converter.canWrite(getClass(), MediaType.MULTIPART_FORM_DATA));
    Assert.assertFalse(converter.canWrite(getClass(), MediaType.TEXT_HTML));
    Assert.assertFalse(converter.canWrite(getClass(), MediaType.TEXT_PLAIN));
    Assert.assertFalse(converter.canWrite(getClass(), MediaType.TEXT_XML));
  }

  /**
   * Tests that an object cannot be read from a Protocol Buffer message if the
   * content is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testReadWithInvalidContent() throws IOException
  {
    new MockHttpInputMessage((byte[]) null);
  }

  /**
   * Tests that an object cannot be read from a Protocol Buffer message if the
   * content type is invalid.
   */
  @Test(expected = HttpMessageNotReadableException.class)
  public void testReadWithInvalidContentType() throws IOException
  {
    try (final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        final ObjectOutputStream writer = new ObjectOutputStream(stream))
    {
      writer.writeObject(new Message());

      final MockHttpInputMessage inputMessage = new MockHttpInputMessage(stream.toByteArray());
      inputMessage.getHeaders().setContentType(MediaType.APPLICATION_JSON);

      converter.read(Message.class, inputMessage);
    }
  }

  /**
   * Tests that an object can be read successfully from a Protocol Buffer message.
   */
  @Test
  public void testReadWithValidContentType() throws IOException
  {
    final MockHttpInputMessage inputMessage = new MockHttpInputMessage(ProtobufIOUtil.toByteArray(new Message()
        , RuntimeSchema.getSchema(Message.class)
        , LinkedBuffer.allocate()));
    inputMessage.getHeaders().setContentType(ProtocolBufferHttpMessageConverter.MEDIA_TYPE);

    final Message output = (Message) converter.read(Message.class, inputMessage);

    Assert.assertNotNull(output);
    Assert.assertNotEquals(0, output.b);
    Assert.assertNotEquals(0, output.d);
    Assert.assertNotEquals(0, output.f);
    Assert.assertNotEquals(0, output.i);
    Assert.assertNotEquals(0, output.l);
    Assert.assertNotNull(output.s);
  }

  /**
   * Tests that the {@code supports} method throws an exception if invoked.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testSupports()
  {
    converter.supports(getClass());
  }

  /**
   * Tests that a Protocol Buffer message cannot be written if the content
   * is invalid.
   */
  @Test(expected = NullPointerException.class)
  public void testWriteWithInvalidContent() throws IOException
  {
    final MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
    converter.write(null, ProtocolBufferHttpMessageConverter.MEDIA_TYPE, outputMessage);

    Assert.assertEquals(0, outputMessage.getBodyAsBytes().length);
  }

  /**
   * Tests that an object cannot be written as a Protocol Buffer message if the
   * content type is invalid.
   */
  @Test(expected = HttpMessageNotWritableException.class)
  public void testWriteWithInvalidContentType() throws IOException
  {
    converter.write(new Message(), MediaType.APPLICATION_JSON, new MockHttpOutputMessage());
  }

  /**
   * Tests that an object can be written successfully as a Protocol Buffer message.
   */
  @Test
  public void testWriteWithValidContentType() throws IOException
  {
    final MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
    converter.write(new Message(), ProtocolBufferHttpMessageConverter.MEDIA_TYPE, outputMessage);

    Assert.assertTrue(outputMessage.getHeaders().getContentType().isCompatibleWith(ProtocolBufferHttpMessageConverter.MEDIA_TYPE));
    Assert.assertNotEquals(0, outputMessage.getBodyAsBytes().length);
  }
}

/**
 * A sample Protocol Buffer message.
 */
class Message implements Serializable
{
  byte   b = 8;
  double d = 2.9d;
  float  f = 3.5f;
  int    i = 4;
  long   l = 7;
  String s = UUID.randomUUID().toString();
}
