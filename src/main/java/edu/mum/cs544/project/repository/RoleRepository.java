package edu.mum.cs544.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.mum.cs544.project.model.Role;


@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer>{
}
