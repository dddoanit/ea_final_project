/**
* Author: DatDoan
* Created Date: Apr 7, 2018
*/
package edu.mum.cs544.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Project {
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private int id;
  
  private String name;
  
  @Lob
  private String desciption;
  
  private String location;
  
  @Temporal(TemporalType.DATE)
  private Date startDate;
  
  @Temporal(TemporalType.DATE)
  private Date endDate;
  
  @Column(name = "STATUS")
  @Enumerated(EnumType.STRING)
  private ProjectStatusEnum status;
  
  @OneToMany(mappedBy="project", cascade = CascadeType.ALL)
  private List<ProjectSkill> projectSkills = new ArrayList<>();
  
  
  public void setId(int id) {
    this.id = id;
  }

  public String getDesciption() {
    return desciption;
  }

  public void setDesciption(String desciption) {
    this.desciption = desciption;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public ProjectStatusEnum getStatus() {
    return status;
  }

  public void setStatus(ProjectStatusEnum status) {
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public List<ProjectSkill> getProjectSkills() {
    return projectSkills;
  }

  public void setProjectSkills(List<ProjectSkill> projectSkills) {
    this.projectSkills = projectSkills;
  }

}
