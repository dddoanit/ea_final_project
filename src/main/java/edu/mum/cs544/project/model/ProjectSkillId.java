/**
* Author: DatDoan
* Created Date: Apr 20, 2018
*/
package edu.mum.cs544.project.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectSkillId implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private int projectId;
  private int skillId;
  public int getProjectId() {
    return projectId;
  }
  public void setProjectId(int projectId) {
    this.projectId = projectId;
  }
  public int getSkillId() {
    return skillId;
  }
  public void setSkillId(int skillId) {
    this.skillId = skillId;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + projectId;
    result = prime * result + skillId;
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
    ProjectSkillId other = (ProjectSkillId) obj;
    if (projectId != other.projectId)
      return false;
    if (skillId != other.skillId)
      return false;
    return true;
  }
  
}
