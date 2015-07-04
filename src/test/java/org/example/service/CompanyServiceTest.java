package org.example.service;

import org.example.model.Company;
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
 * Integration tests for {@link CompanyService}.
 */
@ContextConfiguration(locations = { "classpath:springDataContext.xml", "classpath:springServiceContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CompanyServiceTest
{
  @Autowired
  private CompanyService service;

  /**
   * Tests that all available companies can be loaded successfully.
   */
  @Test
  public void testGetAll()
  {
    final List<Company> companies = service.getAll();

    Assert.assertNotNull(companies);
    Assert.assertNotEquals(0, companies.size());
  }
}
