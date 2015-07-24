package org.example.web.controller.business;

import org.example.model.business.Company;
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
  @RequestMapping(method = RequestMethod.GET, value = "/graph")
  @ResponseBody
  public List<Company> api()
  {
    return service.getAll();
  }

  /**
   * Displays a page with details on making requests to the REST API endpoint.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/graph.html")
  public String page()
  {
    return "thymeleaf/graph";
  }
}
