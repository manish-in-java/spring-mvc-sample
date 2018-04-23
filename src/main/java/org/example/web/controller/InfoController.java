package org.example.web.controller;

import org.example.model.Information;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * REST controller for providing current information on the container.
 */
@Controller
public class InfoController
{
  static final String PATH_JSON  = "/rest.json";
  static final String PATH_PAGE  = "/rest.html";
  static final String PATH_PROTO = "/rest.proto";
  static final String PATH_REST  = "/rest";
  static final String PATH_XML   = "/rest.xml";

  static final String VIEW_PAGE = "thymeleaf/info";

  /**
   * Provides current information on the container.
   */
  @GetMapping(PATH_REST)
  @ResponseBody
  public Information api()
  {
    return Information.getInstance();
  }

  /**
   * Displays a page with details on making requests to the REST API endpoint.
   */
  @RequestMapping(PATH_PAGE)
  public String page()
  {
    return VIEW_PAGE;
  }
}
