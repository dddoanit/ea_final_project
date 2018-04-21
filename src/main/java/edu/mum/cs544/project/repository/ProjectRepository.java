package edu.mum.cs544.project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs544.project.model.Project;
import edu.mum.cs544.project.model.ProjectStatusEnum;


/**
* Project Repository
* 
* @author Binyam H
*
*/

@Repository
public interface ProjectRepository  extends JpaRepository<Project,Integer>{
	List<Project>findByNameAllIgnoreCase(String name);
	List<Project>findByDescriptionAllIgnoreCase(String desc);
	List<Project>findByLocationAllIgnoreCase(String loc);
	List<Project>findByStartDateAllIgnoreCase(Date startDate);
	List<Project>findByEndDateAllIgnoreCase(Date endDate);
	/*List<Project>findByProjectStatusEnumAllIgnoreCase(ProjectStatusEnum status);*/
	
	
}
