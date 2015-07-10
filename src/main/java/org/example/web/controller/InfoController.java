package org.example.web.controller;

import org.example.model.Information;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * REST controller for providing current information on the container.
 */
@Controller
public class InfoController
{
  /**
   * Provides current information on the container.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/rest")
  @ResponseBody
  public Information api()
  {
    return Information.getInstance();
  }

  /**
   * Displays a page with details on making requests to the REST API endpoint.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/rest.html")
  public String page()
  {
    return "info";
  }
}
