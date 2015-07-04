package org.example.data;

import org.example.model.Company;
import org.example.model.Project;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Integration tests for {@link CompanyRepository}.
 */
@ContextConfiguration(locations = "classpath:springDataContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
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
      Assert.assertNotNull(company.getID());
      Assert.assertNotNull(company.getName());

      Assert.assertNotNull(company.getProjects());
      Assert.assertNotEquals(0, company.getProjects().size());

      for (final Project project : company.getProjects())
      {
        Assert.assertNotNull(project);
        Assert.assertNotNull(project.getCompany());
        Assert.assertNotNull(project.getID());
        Assert.assertNotNull(project.getName());
      }
    }
  }

  /**
   * Tests that a company can be saved successfully.
   */
  @Test
  public void testSave()
  {
    final Company apple = new Company("Apple");

    Assert.assertNotNull(apple.getProjects());
    Assert.assertEquals(0, apple.getProjects().size());

    apple.addProject(new Project(apple, "Macintosh"));
    apple.addProject(new Project(apple, "iMac"));
    apple.addProject(new Project(apple, "Macbook"));

    Assert.assertNotNull(repository.saveAndFlush(apple));
    Assert.assertNotNull(apple.getProjects());
    Assert.assertNotEquals(0, apple.getProjects().size());
  }
}
