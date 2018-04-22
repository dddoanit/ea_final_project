package edu.mum.cs544.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.mum.cs544.project.model.Role;
import edu.mum.cs544.project.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public List<Role> getAll() {
		return roleRepository.findAll();
	}
	
	public Role findOne(int id) {
	  return roleRepository.findOne(id);
	}

}
