package edu.mum.cs544.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.mum.cs544.project.model.Project;
import edu.mum.cs544.project.model.ProjectSkill;
import edu.mum.cs544.project.model.ProjectSkillId;
import edu.mum.cs544.project.repository.ProjectRepository;
import edu.mum.cs544.project.repository.ProjectSkillRepository;


/**
 * Project Services
 * 
 * @author Binyam H
 *
 */

@Service
@Transactional
public class ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private ProjectSkillRepository projectSkillRepository;

  public Project save(Project project) {
    return projectRepository.save(project);
  }

  public void delete(int id) {
    Project project = projectRepository.findOne(id);

    projectRepository.delete(project);
  }

  public List<Project> findAll() {
    return projectRepository.findAll();
  }

  public Project findById(int id) {
    return projectRepository.findOne(id);
  }

  public Project findByName(String name) {
    List<Project> projects = projectRepository.findByNameAllIgnoreCase(name);
    if (projects.size() == 1) {
      return projects.get(0);
    }
    return null;
  }

  public void removeSkill(ProjectSkill projectSkill) {
    System.out.println("Skill size: " + projectSkillRepository.findAll().size());
    projectSkillRepository.delete(projectSkill);
  }


}
