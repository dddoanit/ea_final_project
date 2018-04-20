package edu.mum.cs544.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.mum.cs544.project.model.User;
import edu.mum.cs544.project.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public void delete(long id) {
	  User user = userRepository.findOne(id);
	  user.clearRoles();
	  userRepository.delete(user);
	}
	
	public List<User> findAll() {
	  return userRepository.findAll();
	}
	
	public User findById(long id) {
	  return userRepository.findOne(id);
	}
	
	public User findByEmail(String email) {
		List<User> users = userRepository.findByEmailAllIgnoreCase(email);
		if (users.size() == 1) {
			return users.get(0);
		}
		return null;
	}

}
