/**
* Author: Binyam H
* Created Date: Apr 21, 2018
*/
package edu.mum.cs544.project.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.cs544.project.model.Project;

import edu.mum.cs544.project.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectRestController {
  @Autowired
  private ProjectService projectService;
  
  @PostMapping("/save")
  public Project save(@RequestBody Project project) {
    return projectService.save(project);
  }
  
  @PostMapping("/delete/{id}")
  public void delete(@PathVariable("id") int id) {
    projectService.delete(id);
  }
  
  @GetMapping("/findById/{id}")
  public Project findById(@PathVariable("id") int id) {
    return projectService.findById(id);
  }
  @GetMapping("/findAll")
  public List<Project> findAll() {
    return projectService.findAll();
  }
  
}
