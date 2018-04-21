package edu.mum.cs544.project.controller;

import java.util.ArrayList;
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
import edu.mum.cs544.project.model.Skill;
import edu.mum.cs544.project.model.User;
import edu.mum.cs544.project.service.CommentService;
import edu.mum.cs544.project.service.ProjectService;
import edu.mum.cs544.project.service.UserService;

@Controller
@RequestMapping("/project")
public class ProjectDetailController {

  @Autowired
  private ProjectService projectService;

  @Autowired
  private CommentService commentService;

  @Autowired
  private SessionListener sessionListener;

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
  public String showDetails(Model model, @PathVariable("id") int id,
      @ModelAttribute("comment") Comment comment) {
    Project project = projectService.findById(id);
    model.addAttribute("project", project);
    List<Skill> skills = new ArrayList<>();

    for (ProjectSkill e : project.getProjectSkills()) {
      Skill addedSkill = e.getSkill();
      addedSkill.setNumRes(e.getNumResource());
      skills.add(addedSkill);
    }
    model.addAttribute("skills", skills);
    boolean isJoined = false;
    for (User user : project.getUsers()) {
      if (user.getId() == sessionListener.getUser().getId()) {
        isJoined = true;
        break;
      }
    }
    model.addAttribute("isJoined", isJoined);
    model.addAttribute("comments", project.getProjectComments());
    return "project-details";
  }

  /**
   * addComment()
   * 
   * @author Lwin Moe Aung
   */
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
    return "redirect:/project/details/" + projectId;
  }

  /**
   * removeComment()
   * 
   * @author Lwin Moe Aung
   */
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
    return "redirect:/project/details/" + projectId;
  }

  /**
   * getAllComments()
   * 
   * @author Lwin Moe Aung
   */
  @ModelAttribute("allComments")
  public List<Comment> getAllComments() {
    return commentService.findAll();
  }

}
