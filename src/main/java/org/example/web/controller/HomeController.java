package org.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the home page.
 */
@Controller
public class HomeController
{
  static final String PATH_HOME = "/";

  static final String VIEW_HOME = "thymeleaf/home";

  /**
   * Displays the home page.
   */
  @GetMapping(PATH_HOME)
  public String home()
  {
    return VIEW_HOME;
  }
}
