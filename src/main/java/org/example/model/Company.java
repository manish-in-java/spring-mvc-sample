package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a company.
 */
@Entity
@Table(name = "company")
public class Company extends Model
{
  @Column(name = "name")
  @NotNull
  @Size(max = 100)
  private String name;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "company", orphanRemoval = true)
  private Set<Project> projects;

  /**
   * Deliberately hidden to prevent direct instantiation.
   */
  Company()
  {
    super();
  }

  /**
   * Creates a company with a specified name.
   *
   * @param name The company name.
   */
  public Company(final String name)
  {
    this();

    this.name = name;
  }

  /**
   * Adds a project for this company.
   *
   * @param project The project to add.
   */
  public void addProject(final Project project)
  {
    if (projects == null)
    {
      projects = new HashSet<Project>();
    }

    projects.add(project);
  }

  /**
   * Gets the company name.
   *
   * @return The company name.
   */
  public String getName()
  {
    return name;
  }

  /**
   * Gets the projects run by this company.
   *
   * @return A {@link Set} of {@link Project}s.
   */
  public Set<Project> getProjects()
  {
    return Collections.unmodifiableSet(projects != null ? projects : Collections.<Project>emptySet());
  }
}
