package org.example.model.business;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a project.
 */
public class Project
{
  @JsonIgnore
  private Company company;

  private String name;

  /**
   * Deliberately hidden to prevent direct instantiation.
   */
  Project()
  {
    super();
  }

  /**
   * Creates a project with a specified name for a specified company.
   *
   * @param company The company that runs the project.
   * @param name    The project name.
   */
  public Project(final Company company, final String name)
  {
    this();

    this.company = company;
    this.name = name;
  }

  /**
   * Gets the company that runs the project.
   *
   * @return The company that runs the project.
   */
  public Company getCompany()
  {
    return company;
  }

  /**
   * Gets the project name.
   *
   * @return The project name.
   */
  public String getName()
  {
    return name;
  }
}
