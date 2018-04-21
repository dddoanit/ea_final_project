package edu.mum.cs544.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.mum.cs544.project.model.ProjectSkill;
import edu.mum.cs544.project.model.ProjectSkillId;


@Repository
public interface ProjectSkillRepository  extends JpaRepository<ProjectSkill, ProjectSkillId>{
}
