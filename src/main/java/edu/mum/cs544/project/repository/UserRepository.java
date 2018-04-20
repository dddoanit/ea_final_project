package edu.mum.cs544.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.mum.cs544.project.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{
	List<User> findByEmailAllIgnoreCase(String email);
}
