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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.IntSequenceGenerator.class, 
		  property = "id")
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

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public Skill getSkill() {
    return skill;
  }

  public void setSkill(Skill skill) {
    this.skill = skill;
  }

  public ProjectSkillId getPk() {
    return pk;
  }

  public void setPk(ProjectSkillId pk) {
    this.pk = pk;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((pk == null) ? 0 : pk.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProjectSkill other = (ProjectSkill) obj;
    if (pk == null) {
      if (other.pk != null)
        return false;
    } else if (!pk.equals(other.pk))
      return false;
    return true;
  }
  
}
