package org.example.web;

import org.example.model.Choice;
import org.example.model.Day;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Page controller for reading enumeration values from HTTP requests.
 */
@Controller
public class EnumController
{
  /**
   * Displays the home page.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/")
  public String home()
  {
    return "home";
  }

  /**
   * Displays the option chosen by the user as collected using a model object.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/model")
  public String model(Choice choice)
  {
    return "model";
  }

  /**
   * Displays the option chosen by the user as collected using a request parameter.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/param")
  public String param(@RequestParam Day day, Model model)
  {
    model.addAttribute("day", day);

    return "param";
  }
}
