/**
* Author: DatDoan
* Created Date: Apr 20, 2018
*/
package edu.mum.cs544.project.utils;

import java.util.List;

public class SearchProjectParam {
  private String name;
  private String status;
  private String location;
  private List<Integer> skills;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public List<Integer> getSkills() {
    return skills;
  }
  public void setSkills(List<Integer> skills) {
    this.skills = skills;
  }
  
}
