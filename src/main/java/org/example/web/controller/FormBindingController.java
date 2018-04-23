package org.example.web.controller;

import org.example.model.Choice;
import org.example.model.Day;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Page controller for reading enumeration values from HTTP requests.
 */
@Controller
public class FormBindingController
{
  static final String PARAM_DAY = "day";

  static final String PATH_FORM  = "/form";
  static final String PATH_MODEL = "/model";
  static final String PATH_PARAM = "/param";

  static final String VIEW_FORM  = "thymeleaf/form";
  static final String VIEW_MODEL = "thymeleaf/model";
  static final String VIEW_PARAM = "thymeleaf/param";

  /**
   * Displays the form binding page.
   */
  @GetMapping(PATH_FORM)
  public String form()
  {
    return VIEW_FORM;
  }

  /**
   * Displays the option chosen by the user as collected using a model object.
   */
  @PostMapping(PATH_MODEL)
  public String model(final Choice choice)
  {
    return VIEW_MODEL;
  }

  /**
   * Displays the option chosen by the user as collected using a request parameter.
   */
  @PostMapping(PATH_PARAM)
  public String param(@RequestParam final Day day, final Model model)
  {
    model.addAttribute(PARAM_DAY, day);

    return VIEW_PARAM;
  }
}
