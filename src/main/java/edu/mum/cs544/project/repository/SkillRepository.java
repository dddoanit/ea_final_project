package edu.mum.cs544.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.cs544.project.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
	@Query("select distinct s from Skill s where s.name = :name")
	Skill findByName(@Param("name") String name);
}
