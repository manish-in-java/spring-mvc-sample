package org.example.web.controller;

import org.example.model.Choice;
import org.example.model.Day;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Page controller for reading enumeration values from HTTP requests.
 */
@Controller
public class EnumController
{
  static final String PARAM_DAY = "day";

  static final String PATH_HOME  = "/";
  static final String PATH_MODEL = "/model";
  static final String PATH_PARAM = "/param";

  static final String VIEW_HOME  = "thymeleaf/home";
  static final String VIEW_MODEL = "thymeleaf/model";
  static final String VIEW_PARAM = "thymeleaf/param";

  /**
   * Displays the home page.
   */
  @RequestMapping(method = RequestMethod.GET, value = PATH_HOME)
  public String home()
  {
    return VIEW_HOME;
  }

  /**
   * Displays the option chosen by the user as collected using a model object.
   */
  @RequestMapping(method = RequestMethod.POST, value = PATH_MODEL)
  public String model(Choice choice)
  {
    return VIEW_MODEL;
  }

  /**
   * Displays the option chosen by the user as collected using a request parameter.
   */
  @RequestMapping(method = RequestMethod.POST, value = PATH_PARAM)
  public String param(@RequestParam Day day, Model model)
  {
    model.addAttribute(PARAM_DAY, day);

    return VIEW_PARAM;
  }
}
