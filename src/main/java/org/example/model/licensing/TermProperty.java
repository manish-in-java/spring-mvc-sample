package org.example.model.licensing;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a property that describes the term for which a
 * software license is sold.
 */
public class TermProperty extends Property
{
  private int duration;

  /**
   * Creates a term property with a specified acronym and name and duration.
   *
   * @param acronym  The acronym for the property.
   * @param name     The property name.
   * @param duration The duration for which the license is sold.
   */
  public TermProperty(@JsonProperty("acronym") final String acronym
      , @JsonProperty("name") final String name
      , @JsonProperty("duration") final int duration)
  {
    super(acronym, name);

    this.duration = duration;
  }

  /**
   * Gets the duration for which the license is sold.
   *
   * @return The duration for which the license is sold.
   */
  public int getDuration()
  {
    return duration;
  }
}
