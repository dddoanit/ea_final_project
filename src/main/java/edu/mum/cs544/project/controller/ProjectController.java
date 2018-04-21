package edu.mum.cs544.project.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.cs544.project.config.SessionListener;
import edu.mum.cs544.project.model.Comment;
import edu.mum.cs544.project.model.Project;
import edu.mum.cs544.project.model.ProjectSkill;
import edu.mum.cs544.project.model.ProjectStatusEnum;
import edu.mum.cs544.project.model.Skill;
import edu.mum.cs544.project.model.User;
import edu.mum.cs544.project.service.CommentService;
import edu.mum.cs544.project.service.ProjectService;
import edu.mum.cs544.project.service.SkillService;
import edu.mum.cs544.project.service.UserService;


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

  @Autowired
  private SkillService skillService;
  
  @Autowired
  private CommentService commentService;
  
  @Autowired
  private SessionListener sessionListener;
	  
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String list(Model model) {
    model.addAttribute("projects", projectService.findAll());
    return "admin/project/index";
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public String get(Model model, @ModelAttribute("project") Project project,
      @ModelAttribute("skill") Skill skill, @ModelAttribute("comment") Comment comment,
      @RequestParam(value = "id", required = false) Integer id) {
    if (id != null) {
      project = projectService.findById(id);
      List<Skill> skills = new ArrayList<>();
      List<Comment> comments = new ArrayList<>();
      
      for (ProjectSkill e: project.getProjectSkills()) {
        Skill addedSkill = e.getSkill();
        addedSkill.setNumRes(e.getNumResource());
        skills.add(addedSkill);
      }
      model.addAttribute("skills", skills);
      
      for (Comment e: project.getProjectComments()) {
          comments.add(e);
        }
        model.addAttribute("comments", comments);
    }
    model.addAttribute("project", project);

    return "admin/project/create";
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String create(Model model, @ModelAttribute("project") Project project) {
    String view = "redirect:/admin/project/";
    Project existingProject = projectService.findByName(project.getName());
    if (existingProject != null && project.getId() == 0) {
      model.addAttribute("errorMsg",
          "This Name is already taken. Please use another Project Name.");
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

  @PostMapping("/skill/add")
  public String addSkill(Model model, @ModelAttribute("skill") Skill skill,
      @RequestParam(value = "projectId", required = true) Integer projectId) {
    Project project = projectService.findById(projectId);
    List<Skill> skills = getAllSkills();
    for (Skill selectedSkill : skills) {
      if (selectedSkill.getId() == skill.getId()) {
        ProjectSkill projectSkill = new ProjectSkill(project, selectedSkill);
        projectSkill.setNumResource(skill.getNumRes());
        project.getProjectSkills().add(projectSkill);
        break;
      }
    }
    projectService.save(project);
    return "redirect:/admin/project/create?id=" + projectId;
  }
  
	@PostMapping("/comment/add")
	public String addComment(Model model, @ModelAttribute("comment") Comment comment,
			@RequestParam(value = "projectId", required = true) Integer projectId) {
		User existingUser = userService.findByEmail(sessionListener.getUser().getEmail());
		comment.setUser(existingUser);
		comment.setDate(new Date());		
		comment.setEmail(sessionListener.getUser().getEmail());
		Project project = projectService.findById(projectId);
		comment.setProject(project);
		List<Comment> comments = getAllComments();
		if (comments.size() > 0) {
			for (Comment selectedComment : comments) {
				project.getProjectComments().add(selectedComment);
			}
		}		
		project.getProjectComments().add(comment);
		projectService.save(project);
		return "redirect:/admin/project/create?id=" + projectId;
	}
  
  @RequestMapping("/skill/remove/{skillId}")
  public String removeSkill(Model model, @PathVariable("skillId") Integer skillId,
      @RequestParam(value = "projectId", required = true) Integer projectId) {
    Project project = projectService.findById(projectId);
    for (ProjectSkill selectedSkill : project.getProjectSkills()) {
      if (selectedSkill.getPk().getSkillId() == skillId) {
        projectService.removeSkill(selectedSkill);
        break;
      }
    }
    return "redirect:/admin/project/create?id=" + projectId;
  }
  
  @RequestMapping("/comment/remove/{commentId}")
  public String removeComment(Model model, @PathVariable("commentId") Long commentId,
      @RequestParam(value = "projectId", required = true) Integer projectId) {
    Project project = projectService.findById(projectId);    
    commentService.delete(commentId);
    for (Comment selectedComment : project.getProjectComments()) {
    	if (selectedComment.getId() == commentId) {
    		projectService.removeComment(selectedComment);
    		break;
    	}
      }    
    return "redirect:/admin/project/create?id=" + projectId;
  }

  @ModelAttribute("allSkills")
  public List<Skill> getAllSkills() {
    return skillService.findAll();
  }

  @ModelAttribute("allComments")
  public List<Comment> getAllComments() {
    return commentService.findAll();
  }
  
}
