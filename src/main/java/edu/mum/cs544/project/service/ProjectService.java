package edu.mum.cs544.project.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cs544.project.model.Project;
import edu.mum.cs544.project.repository.ProjectRepository;


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
	public Project findByDescription(String description) {
		List<Project> projects = projectRepository.findByDesciptionAllIgnoreCase((description));
		if (projects.size() == 1) {
			return projects.get(0);
		}
		return null;
	}
	public Project findByLocation(String location) {
		List<Project> projects = projectRepository.findByLocationAllIgnoreCase((location));
		if (projects.size() == 1) {
			return projects.get(0);
		}
		return null;
	}
	public Project findByStartDate(Date startDate) {
		List<Project> projects = projectRepository.findByStartDateAllIgnoreCase((startDate));
		if (projects.size() == 1) {
			return projects.get(0);
		}
		return null;
	}
	public Project findByEndDate(Date endDate) {
		List<Project> projects = projectRepository.findByStartDateAllIgnoreCase((endDate));
		if (projects.size() == 1) {
			return projects.get(0);
		}
		return null;
	}
	

}
