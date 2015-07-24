package org.example.web.controller.licensing;

import org.example.model.licensing.Edition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for managing software editions.
 */
@Controller
public class EditionController
{
  /**
   * Demonstrates serialization and deserialization from and to Java objects.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/generics")
  @ResponseBody
  public Edition check(@RequestBody final Edition edition)
  {
    return edition;
  }

  /**
   * Displays a page with details on making requests to the REST API endpoint.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/generics.html")
  public String page()
  {
    return "thymeleaf/generics";
  }
}
