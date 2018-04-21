package edu.mum.cs544.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.cs544.project.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectDetailController {

	@Autowired
	  private ProjectService projectService;
	 @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	  public String showDetails(Model model, @PathVariable("id") int id) {
	   model.addAttribute(projectService.findById(id));
	    return "project-details";
	  }
}
