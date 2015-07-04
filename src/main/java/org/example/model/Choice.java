package org.example.model;

/**
 * Represents a choice made by an application user.
 */
public class Choice
{
  private Day day;

  /**
   * Gets the day of the week chosen by the user.
   *
   * @return A {@link Day}.
   */
  public Day getDay()
  {
    return day;
  }

  /**
   * Sets the day of the week chosen by the user.
   *
   * @param day A {@link Day}.
   */
  public void setDay(Day day)
  {
    this.day = day;
  }
}
