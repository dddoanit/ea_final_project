package edu.mum.cs544.project.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import edu.mum.cs544.project.model.Project;
import edu.mum.cs544.project.model.ProjectStatusEnum;
import edu.mum.cs544.project.service.ProjectService;


/**
 * Project Controller
 * 
 * @author Binyam H
 *
 */
@Controller
@RequestMapping("/admin/project")
public class ProjectController {

 

  @Autowired
  private ProjectService projectService;

  // @Autowired
  // private PasswordEncoder encoder;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String list(Model model) {
    model.addAttribute("projects", projectService.findAll());
    return "admin/project/index";
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public String get(Model model, @ModelAttribute("project") Project project,
      @RequestParam(value = "id", required = false) Integer id) {
    if (id != null) {
       project = projectService.findById(id);
     
      }
      model.addAttribute("project", project);
    
    return "admin/project/create";
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String create(Model model, @ModelAttribute("project") Project project) {
    String view = "redirect:/admin/project/";
    Project  existingProject = projectService.findByName(project.getName());
    if(existingProject != null && project.getId() == 0) {
      model.addAttribute("errorMsg", "This Name is already taken. Please use another Project Name.");
      view = "admin/project/create";
      return view;
    } else {
      projectService.save(project);
    }
    return view;
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
  public String delete(@PathVariable("id") int id) {
    projectService.delete(id);
    return "redirect:/admin/project/";
  }

  @ModelAttribute("projectStatuses")
  public List<ProjectStatusEnum> productTypes() {
      return Arrays.asList(ProjectStatusEnum.values());
  }
 
}
