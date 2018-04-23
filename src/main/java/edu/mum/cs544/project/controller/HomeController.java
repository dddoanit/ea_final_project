package edu.mum.cs544.project.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import edu.mum.cs544.project.model.ProjectStatusEnum;
import edu.mum.cs544.project.model.Skill;
import edu.mum.cs544.project.service.ProjectService;
import edu.mum.cs544.project.service.SkillService;
import edu.mum.cs544.project.utils.SearchProjectParam;

@Controller
public class HomeController {

  @Autowired
  private ProjectService projectService;
  
  @Autowired
  private SkillService skillService;

  @GetMapping({"/", "/index", "/home", "/search"})
  public String homePage(Model model, @ModelAttribute("search") SearchProjectParam searchProjectParam) {
    model.addAttribute("projects", projectService.findAll());
    return "index";
  }
  
  @PostMapping({"/search"})
  public String search(Model model, @ModelAttribute("search") SearchProjectParam params) {
    model.addAttribute("projects", projectService.search(params));
    return "index";
  }

  @GetMapping({"/secure"})
  public String securePage() {
    return "secure";
  }
  
  @ModelAttribute("projectStatuses")
  public List<ProjectStatusEnum> productTypes() {
    return Arrays.asList(ProjectStatusEnum.values());
  }
  
  @ModelAttribute("allSkills")
  public List<Skill> getAllSkills() {
    return skillService.findAll();
  }

}
