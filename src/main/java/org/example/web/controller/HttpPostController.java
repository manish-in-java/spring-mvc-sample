package org.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Page controller for reading request parameters from HTTP POST requests.
 */
@Controller
public class HttpPostController
{
  static final String PARAM_NAME_FIRST = "fn";
  static final String PARAM_NAME_LAST  = "sn";

  static final String PATH_POST = "/post";

  /**
   * Reads request parameters from an HTTP POST request.
   */
  @PostMapping(PATH_POST)
  @ResponseBody
  public String read(@RequestParam(required = false, value = PARAM_NAME_FIRST) final String firstName
      , @RequestParam(required = false, value = PARAM_NAME_LAST) final String surname)
  {
    return String.format("Name=%s %s", firstName, surname);
  }
}
