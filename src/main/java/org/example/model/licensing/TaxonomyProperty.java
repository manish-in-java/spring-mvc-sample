package org.example.model.licensing;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a property that describes the taxonomy under which a
 * software license is sold.
 */
public class TaxonomyProperty extends Property
{
  /**
   * Creates a taxonomy property with a specified acronym and name.
   *
   * @param acronym The acronym for the property.
   * @param name    The property name.
   */
  public TaxonomyProperty(@JsonProperty("acronym") final String acronym
      , @JsonProperty("name") final String name)
  {
    super(acronym, name);
  }
}
