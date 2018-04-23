/**
* Author: Lwin Moe Aung
* Created Date: Apr 23, 2018
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
import edu.mum.cs544.project.model.Comment;
import edu.mum.cs544.project.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentRestController {
  @Autowired
  private CommentService commentService;
  
  @PostMapping("/save")
  public Comment save(@RequestBody Comment comment) {
    return commentService.save(comment);
  }
  
  @PostMapping("/delete/{id}")
  public void delete(@PathVariable("id") int id) {
	  commentService.delete(id);
  }
  
  @GetMapping("/findById/{id}")
  public Comment findById(@PathVariable("id") int id) {
    return commentService.findById(id);
  }
  @GetMapping("/findAll")
  public List<Comment> findAll() {
    return commentService.findAll();
  }
  
}
