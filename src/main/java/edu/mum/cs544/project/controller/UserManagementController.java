package edu.mum.cs544.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import edu.mum.cs544.project.model.Role;
import edu.mum.cs544.project.model.User;
import edu.mum.cs544.project.service.RoleService;
import edu.mum.cs544.project.service.UserService;


/**
 * User Management Controller
 * 
 * @author DatDoan
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserManagementController {

  @Autowired
  private RoleService roleService;

  @Autowired
  private UserService userService;

  // @Autowired
  // private PasswordEncoder encoder;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String list(Model model) {
    model.addAttribute("users", userService.findAll());
    return "admin/user/index";
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public String get(Model model, @ModelAttribute("user") User user,
      @RequestParam(value = "id", required = false) Long id) {
    if (id != null) {
      User updatedUser = userService.findById(id);
      if (updatedUser.getRoles().size() == 1) {
        updatedUser.setRole(updatedUser.getRoles().get(0).getId());
      }
      model.addAttribute("user", updatedUser);
      model.addAttribute("skills", updatedUser.getSkills());
    }
    return "admin/user/create";
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String create(Model model, @ModelAttribute("user") User user) {
    String view = "redirect:/admin/user/";
    User existingUser = userService.findByEmail(user.getEmail());
    if(existingUser != null && user.getId() == 0) {
      model.addAttribute("errorMsg", "This email already exists. Please use another email.");
      view = "admin/user/create";
      return view;
    } else {
      if (user.getId() != 0 && user.getPassword().isEmpty()) {
        user.setPassword(existingUser.getPassword());
      }
      user.clearRoles();
      user.addRole(roleService.findOne(user.getRole()));
      userService.save(user);
    }
    return view;
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
  public String delete(@PathVariable("id") Long id) {
    userService.delete(id);
    return "redirect:/admin/user/";
  }

  @ModelAttribute("roles")
  public List<Role> getRoles() {
    return roleService.getAll();
  }
}
