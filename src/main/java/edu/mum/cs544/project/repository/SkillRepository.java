package edu.mum.cs544.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs544.project.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
