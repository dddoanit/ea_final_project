package edu.mum.cs544.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import edu.mum.cs544.project.config.SessionListener;
import edu.mum.cs544.project.model.Project;
import edu.mum.cs544.project.model.Role;
import edu.mum.cs544.project.model.Skill;
import edu.mum.cs544.project.model.User;
import edu.mum.cs544.project.service.ProjectService;
import edu.mum.cs544.project.service.RoleService;
import edu.mum.cs544.project.service.SkillService;
import edu.mum.cs544.project.service.UserService;

@Controller
@RequestMapping("/me/")
public class MyAccountController {


  @Autowired
  private UserService userService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private SessionListener sessionListener;
  
  @Autowired
  private SkillService skillService;
  
  @Autowired
  private ProjectService projectService;

  @Autowired
  private PasswordEncoder encoder;

  // Update Account
  @GetMapping({"/account/update"})
  public String account(Model model, @ModelAttribute("skill") Skill skill) {
    User user = userService.findByEmail(sessionListener.getUser().getEmail());
    model.addAttribute("user", user);
    model.addAttribute("projects", user.getProjects());
    model.addAttribute("skills", user.getSkills());
    return "myaccount/account";
  }

  @PostMapping("/account/update")
  public String updateAccount(Model model, @ModelAttribute("user") User user) {
    User existingUser = userService.findByEmail(sessionListener.getUser().getEmail());
    if (user.getPassword() ==null || user.getPassword().isEmpty()) {
      user.setPassword(existingUser.getPassword());
    }
    user.setSkills(existingUser.getSkills());
    userService.save(user);
    return "redirect:/me/account/update";
  }

  // Sign up
  @GetMapping({"/account/signup"})
  public String signUp(Model model, @ModelAttribute("user") User user) {
    // set the default role for a new user
    user.addRole(roleService.findOne(2));
    user.setEnabled(true);
    
    return "myaccount/signup";
  }

  @PostMapping("/account/signup")
  public String createNewAccount(Model model, @ModelAttribute("user") User user) {
    String view = "myaccount/signup";
    User existingUser = userService.findByEmail(user.getEmail());
    if (existingUser != null) {
      model.addAttribute("errorMsg", "This email already exists. Please use another email.");
      return view;
    }
    userService.save(user);
    model.addAttribute("infoMsg",
        "Your new account has been created sucessfully. Click here to login");
    return view;
  }
  
  @PostMapping("/skill/add")
  public String addSkill(Model model, @ModelAttribute("skill") Skill skill) {
    User existingUser = userService.findByEmail(sessionListener.getUser().getEmail());
    List<Skill> skills = getAllSkills();
    for (Skill selectedSkill: skills) {
      if (selectedSkill.getId() == skill.getId()) {
        existingUser.addSkill(selectedSkill);
        break;
      }
    }
    userService.save(existingUser);
    return "redirect:/me/account/update";
  }
  
  @RequestMapping(value = "/skill/remove/{id}", method = RequestMethod.POST)
  public String removeSkill(@PathVariable("id") int id) {
    User existingUser = userService.findByEmail(sessionListener.getUser().getEmail());
    existingUser.removeSkills(id);
    userService.save(existingUser);
    return "redirect:/me/account/update";
  }

  
  @ModelAttribute("allSkills")
  public List<Skill> getAllSkills() {
    return skillService.findAll();
  }
  
  @PostMapping("/project/join")
  public String joinProject(Model model, @RequestParam(value = "projectId", required = false) Integer projectId) {
    User existingUser = userService.findByEmail(sessionListener.getUser().getEmail());
    Project project = projectService.findById(projectId);
    existingUser.getProjects().add(project);
    userService.save(existingUser);
    return "redirect:/project/details/" + project.getId();
  }
  
  @PostMapping("/project/unjoin")
  public String unjoinProject(Model model, @RequestParam(value = "projectId", required = false) Integer projectId) {
    User existingUser = userService.findByEmail(sessionListener.getUser().getEmail());
    Project project = projectService.findById(projectId);
    for (Project e: existingUser.getProjects()) {
      if (e.getId() == project.getId()) {
        existingUser.getProjects().remove(e);
        break;
      }
    }
    userService.save(existingUser);
    return "redirect:/project/details/" + project.getId();
  }
  
}
