package edu.mum.cs544.project;

import static org.assertj.core.api.Assertions.fail;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.mum.cs544.project.model.Project;
import edu.mum.cs544.project.service.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinalProjectApplication.class)
public class ProjectTests {

  @Autowired
  private ProjectService projectService;
  
  @Test
  public void test() {
    
    // Test insert
    Project project = new Project();
    project.setName("Payment Calculation");
    project.setDescription("My Loan In MUM");
    Date d1 = new Date(2018, 11, 21);
    Date d2 = new Date(2018, 12, 21);
    project.setStartDate(d1);
    project.setStartDate(d2);
   
   Project savedProject = projectService.save(project);
    if(savedProject.getId() == 0) {
      fail("Failed to insert a new project");
    }
    
    // test delete
    projectService.delete(project.getId());
    Project deletedProject = projectService.findById(project.getId());
    if (deletedProject != null) {
      fail("Failed to delete project");
    }
    
  }

}
