package edu.mum.cs544.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import edu.mum.cs544.project.model.User;

@Controller
@RequestMapping("/user")
public class LoginController {

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(@ModelAttribute("user") User user) {
    return "login";
  }
}
