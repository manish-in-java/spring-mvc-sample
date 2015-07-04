package org.example.web;

import org.example.model.Information;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Page controller for providing current information on the container.
 */
@Controller
public class InfoController
{
  /**
   * Provides current information on the container.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/info")
  @ResponseBody
  public Information info()
  {
    return Information.getInstance();
  }
}
