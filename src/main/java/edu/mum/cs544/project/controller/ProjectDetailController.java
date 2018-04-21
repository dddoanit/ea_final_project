package edu.mum.cs544.project.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import edu.mum.cs544.project.config.SessionListener;
import edu.mum.cs544.project.model.Project;
import edu.mum.cs544.project.model.ProjectSkill;
import edu.mum.cs544.project.model.Skill;
import edu.mum.cs544.project.model.User;
import edu.mum.cs544.project.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectDetailController {

  @Autowired
  private ProjectService projectService;
  
  @Autowired
  private SessionListener sessionListener;

  @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
  public String showDetails(Model model, @PathVariable("id") int id) {
    Project project = projectService.findById(id);
    model.addAttribute("project", project);
    List<Skill> skills = new ArrayList<>();
    
    for (ProjectSkill e: project.getProjectSkills()) {
      Skill addedSkill = e.getSkill();
      addedSkill.setNumRes(e.getNumResource());
      skills.add(addedSkill);
    }
    model.addAttribute("skills", skills);
    boolean isJoined = false;
    for (User user: project.getUsers()) {
      if (user.getId() == sessionListener.getUser().getId()) {
        isJoined = true;
        break;
      }
    }
    model.addAttribute("isJoined", isJoined);
    return "project-details";
  }
}
