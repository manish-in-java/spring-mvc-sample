package org.example.data;

import org.example.model.business.Company;
import org.example.model.business.Project;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Integration tests for {@link CompanyRepository}.
 */
@ContextConfiguration(locations = "classpath:springDataContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyRepositoryTest
{
  @Autowired
  private CompanyRepository repository;

  /**
   * Tests that companies can be loaded successfully.
   */
  @Test
  public void testFindAll()
  {
    final List<Company> companies = repository.findAll();

    Assert.assertNotNull(companies);
    Assert.assertNotEquals(0, companies.size());

    for (final Company company : companies)
    {
      Assert.assertNotNull(company);
      Assert.assertNotNull(company.getName());

      Assert.assertNotNull(company.getProjects());
      Assert.assertNotEquals(0, company.getProjects().size());

      for (final Project project : company.getProjects())
      {
        Assert.assertNotNull(project);
        Assert.assertNotNull(project.getCompany());
        Assert.assertNotNull(project.getName());
      }
    }
  }
}
