package org.example.model.business;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a company.
 */
public class Company
{
  private String name;

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
