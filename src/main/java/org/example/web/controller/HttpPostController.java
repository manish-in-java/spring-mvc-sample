package org.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Page controller for reading request parameters from HTTP POST requests.
 */
@Controller
public class HttpPostController
{
  /**
   * Reads request parameters from an HTTP POST request.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/post")
  @ResponseBody
  public String read(@RequestParam(required = false, value = "fn") final String firstName
      , @RequestParam(required = false, value = "sn") final String surname)
  {
    return String.format("Name=%s %s", firstName, surname);
  }
}
