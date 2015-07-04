package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents a project.
 */
@Entity
@Table(name = "project")
public class Project extends Model
{
  @JoinColumn(name = "company_id", nullable = false, updatable = false)
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @NotNull
  private Company company;

  @Column(name = "name", nullable = false)
  @NotNull
  @Size(max = 100)
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
