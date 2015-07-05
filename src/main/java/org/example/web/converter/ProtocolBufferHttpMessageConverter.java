package org.example.web.converter;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * <p>
 * An {@code HttpMessageConverter} that can read and write
 * <a href="https://developers.google.com/protocol-buffers/">Google
 * Protocol Buffer (Protobuf)</a> messages using the open-source
 * <a href="http://http://www.protostuff.io">Protostuff library</a>.
 * Its advantage over native Protobuf serialization and deserialization
 * is that it can work with normal {@code POJO}s, as compared to the
 * native implementation that requires the objects to implement
 * the {@code Message} interface from the Protobuf Java library.  This
 * allows applications to use Protobuf with existing classes instead
 * of having to re-generate them using the Protobuf compiler.
 * </p>
 * <p>
 * Supports the {@code application/x-protobuf} media type. Regular
 * Spring MVC application clients can use this as the media type
 * for the {@code Accept} and {@code Content-Type} HTTP headers
 * for exchanging information as Protobuf messages.
 * </p>
 */
public class ProtocolBufferHttpMessageConverter extends AbstractHttpMessageConverter<Object>
{
  public static final Charset   DEFAULT_CHARSET = Charset.forName("UTF-8");
  public static final MediaType MEDIA_TYPE      = new MediaType("application", "x-protobuf", DEFAULT_CHARSET);

  /**
   * Construct a new instance.
   */
  public ProtocolBufferHttpMessageConverter()
  {
    super(MEDIA_TYPE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canRead(final Class<?> clazz, final MediaType mediaType)
  {
    return canRead(mediaType);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canWrite(final Class<?> clazz, final MediaType mediaType)
  {
    return canWrite(mediaType);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Object readInternal(final Class<?> clazz, final HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException
  {
    if (MEDIA_TYPE.isCompatibleWith(inputMessage.getHeaders().getContentType()))
    {
      final Schema<?> schema = RuntimeSchema.getSchema(clazz);

      final Object value = schema.newMessage();

      try (final InputStream stream = inputMessage.getBody())
      {
        ProtobufIOUtil.mergeFrom(stream, value, (Schema<Object>) schema);

        return value;
      }
    }

    throw new HttpMessageNotReadableException("Unrecognized HTTP media type " + inputMessage.getHeaders().getContentType().getType() + ".");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean supports(final Class<?> clazz)
  {
    // Should not be called, since we override canRead/canWrite.
    throw new UnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void writeInternal(final Object o, final HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException
  {
    if (MEDIA_TYPE.isCompatibleWith(outputMessage.getHeaders().getContentType()))
    {
      try (final OutputStream stream = outputMessage.getBody())
      {
        ProtobufIOUtil.writeTo(stream
            , o
            , RuntimeSchema.getSchema((Class<Object>) o.getClass())
            , LinkedBuffer.allocate());
      }
    }
    else
    {
      throw new HttpMessageNotWritableException("Unrecognized HTTP media type " + outputMessage.getHeaders().getContentType().getType() + ".");
    }
  }
}
