package org.example.service;

import org.example.data.CompanyRepository;
import org.example.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Business logic operations for {@link Company}.
 */
@Service
@Transactional(readOnly = true)
public class CompanyService
{
  @Autowired
  private CompanyRepository companyRepository;

  /**
   * Gets all available companies.
   *
   * @return A {@link List} of companies.
   */
  public List<Company> getAll()
  {
    return companyRepository.findAll();
  }
}
