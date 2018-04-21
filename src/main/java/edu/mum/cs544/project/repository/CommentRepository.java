package edu.mum.cs544.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs544.project.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
