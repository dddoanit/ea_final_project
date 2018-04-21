package edu.mum.cs544.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cs544.project.model.Comment;
import edu.mum.cs544.project.repository.CommentRepository;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	public void delete(long id) {
		Comment comment = commentRepository.findOne(id);
		commentRepository.delete(comment);
	}

	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	public Comment findById(long id) {
		return commentRepository.findOne(id);
	}

}
