package edu.mum.cs544.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import edu.mum.cs544.project.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{
	List<User> findByEmailAllIgnoreCase(String email);
	@Query("select distinct u from User u join u.skills s where s.id = :skillId")
	List<User> findBySkill(@Param("skillId") int skillId);
	@Query("select distinct u from User u join u.roles r  where r.name ='ADMIN'")
	List<User> findAdmin();
	
	
}
