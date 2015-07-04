package org.example.web;

import org.example.model.Company;
import org.example.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Page controller for working with company information.
 */
@Controller
public class CompanyController
{
  @Autowired
  private CompanyService service;

  /**
   * Gets all available companies.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/companies")
  @ResponseBody
  public List<Company> companies()
  {
    return service.getAll();
  }
}
