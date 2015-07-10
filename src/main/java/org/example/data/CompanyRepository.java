package org.example.data;

import org.example.model.business.Company;
import org.example.model.business.Project;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Data access operations on {@link Company}.
 */
@Component
public class CompanyRepository
{
  private static final List<Company> COMPANIES = new LinkedList<>();

  static
  {
    final Company google = new Company("Google");
    google.addProject(new Project(google, "Adwords"));
    google.addProject(new Project(google, "Gmail"));
    google.addProject(new Project(google, "Search"));

    COMPANIES.add(google);

    final Company microsoft = new Company("Microsoft");
    microsoft.addProject(new Project(microsoft, "Office"));
    microsoft.addProject(new Project(microsoft, "Visual Studio"));
    microsoft.addProject(new Project(microsoft, "Windows"));

    COMPANIES.add(microsoft);
  }

  /**
   * Finds all available companies.
   *
   * @return A {@link List} of {@link Company}s.
   */
  public List<Company> findAll()
  {
    return new ArrayList<>(COMPANIES);
  }
}
