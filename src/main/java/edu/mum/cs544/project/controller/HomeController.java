package edu.mum.cs544.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import edu.mum.cs544.project.service.ProjectService;

@Controller
public class HomeController {

  @Autowired
  private ProjectService projectService;

  @GetMapping({"/", "/index", "/home"})
  public String homePage(Model model) {
    model.addAttribute("projects", projectService.findAll());
    return "index";
  }

  @GetMapping({"/secure"})
  public String securePage() {
    return "secure";
  }

}
