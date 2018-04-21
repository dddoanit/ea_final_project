/**
* Author: DatDoan
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
import edu.mum.cs544.project.model.User;
import edu.mum.cs544.project.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
  @Autowired
  private UserService userService;
  
  @PostMapping("/save")
  public User save(@RequestBody User user) {
    return userService.save(user);
  }
  
  @PostMapping("/delete/{id}")
  public void delete(@PathVariable("id") long id) {
    userService.delete(id);
  }
  
  @GetMapping("/findById/{id}")
  public User findById(@PathVariable("id") long id) {
    return userService.findById(id);
  }
  @GetMapping("/findAll")
  public List<User> findAll() {
    return userService.findAll();
  }
  
}
