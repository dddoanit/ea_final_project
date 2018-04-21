/**
* Author: Lwin Moe Aung
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

import edu.mum.cs544.project.model.Skill;
import edu.mum.cs544.project.service.SkillService;

@RestController
@RequestMapping("/api/skill")
public class SkillRestController {
  @Autowired
  private SkillService skillService;
  
  @PostMapping("/save")
  public Skill save(@RequestBody Skill skill) {
    return skillService.save(skill);
  }
  
  @PostMapping("/delete/{id}")
  public void delete(@PathVariable("id") int id) {
	  skillService.delete(id);
  }
  
  @GetMapping("/findById/{id}")
  public Skill findById(@PathVariable("id") int id) {
    return skillService.findById(id);
  }
  @GetMapping("/findAll")
  public List<Skill> findAll() {
    return skillService.findAll();
  }
  
}
