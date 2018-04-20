/**
* Author: DatDoan
* Created Date: Apr 20, 2018
*/
package edu.mum.cs544.project.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class ProjectSkill {
  @EmbeddedId
  private ProjectSkillId pk = new ProjectSkillId();
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "project_id")
  @MapsId("projectId")
  private Project project;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "skill_id")
  @MapsId("skillId")
  private Skill skill;
  
  @Column(name = "num_resource")
  private int numResource;

  public ProjectSkill() {
    
  }
  
  public ProjectSkill(Project project, Skill skill) {
    pk.setProjectId(project.getId());
    pk.setSkillId(skill.getId());
    this.project = project;
    this.skill = skill;
  }
  
  public int getNumResource() {
    return numResource;
  }

  public void setNumResource(int numResource) {
    this.numResource = numResource;
  }
  
}
