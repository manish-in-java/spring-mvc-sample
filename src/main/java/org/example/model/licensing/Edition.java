package org.example.model.licensing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

/**
 * Represents an edition of a software license.
 */
public class Edition implements Serializable
{
  private final String               acronym;
  private final String               id;
  private final Collection<Property> properties;

  /**
   * Creates an edition with a unique identifier, acronym and properties.
   *
   * @param id         The unique identifier for the edition.
   * @param acronym    The acronym for the edition.
   * @param properties The edition properties.
   */
  public Edition(final String id, final String acronym, final Property... properties)
  {
    this(id, acronym, Arrays.asList(properties));
  }

  /**
   * Creates an edition with a unique identifier, acronym and properties.
   *
   * @param id         The unique identifier for the edition.
   * @param acronym    The acronym for the edition.
   * @param properties The edition properties.
   */
  public Edition(@JsonProperty("id") final String id
      , @JsonProperty("acronym") final String acronym
      , @JsonProperty("properties") final Collection<Property> properties)
  {
    this.acronym = acronym;
    this.id = id;
    this.properties = properties;
  }

  /**
   * Gets the acronym for the edition.
   *
   * @return The acronym for the edition.
   */
  public String getAcronym()
  {
    return acronym;
  }

  /**
   * Gets the unique identifier for the edition.
   *
   * @return The unique identifier for the edition.
   */
  public String getId()
  {
    return id;
  }

  /**
   * Gets the edition properties.
   *
   * @return The edition properties.
   */
  public Collection<Property> getProperties()
  {
    return properties;
  }
}
