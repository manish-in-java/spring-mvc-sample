package org.example.model;

import org.springframework.web.context.request.RequestContextHolder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Provides current information on the container.
 */
@XmlRootElement(name = "info")
public class Information
{
  private final String id;
  private final Date   time;

  private Information()
  {
    id = RequestContextHolder.currentRequestAttributes().getSessionId();
    time = new Date();
  }

  public static Information getInstance()
  {
    return new Information();
  }

  @XmlElement(name = "id")
  public String getId()
  {
    return id;
  }

  @XmlElement(name = "time")
  public Date getTime()
  {
    return time;
  }
}
