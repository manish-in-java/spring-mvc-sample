package org.example.model.licensing;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

/**
 * Represents a property of a software license.
 */
@JsonSubTypes({
    @JsonSubTypes.Type(value = TaxonomyProperty.class, name = "taxonomy")
    , @JsonSubTypes.Type(value = TermProperty.class, name = "term")
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public abstract class Property implements Serializable
{
  private final String acronym;
  private final String name;

  /**
   * Creates a property with a specified acronym and name.
   *
   * @param acronym The acronym for the property.
   * @param name    The property name.
   */
  protected Property(final String acronym, final String name)
  {
    this.acronym = acronym;
    this.name = name;
  }

  /**
   * Gets the acronym for the property.
   *
   * @return The acronym for the property.
   */
  public String getAcronym()
  {
    return acronym;
  }

  /**
   * Gets the property name.
   *
   * @return The property name.
   */
  public String getName()
  {
    return name;
  }
}
