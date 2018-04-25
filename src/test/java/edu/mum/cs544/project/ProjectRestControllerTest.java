package edu.mum.cs544.project;

import static org.assertj.core.api.Assertions.fail;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import edu.mum.cs544.project.model.Project;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinalProjectApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectRestControllerTest {

  @LocalServerPort
  private int port;

  private RestTemplate restTemplate = new RestTemplate();
  private String url = "http://localhost:8080/api/project";

  @Before
  public void setup() {
    url = "http://localhost:" + port;
  }

  @SuppressWarnings("deprecation")
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

    Project savedProject =
        restTemplate.postForObject(url + "/api/project/save", project, Project.class);;
    if (savedProject.getId() == 0) {
      fail("Failed to insert a new project");
    }


    // test delete
    restTemplate.postForObject(url + "/api/project/delete/" + savedProject.getId(), null,
        Project.class);
    Project deletedProject = restTemplate
        .getForObject(url + "/api/project/findById/" + savedProject.getId(), Project.class);
    if (deletedProject != null) {
      fail("Failed to delete project");
    }

    // findAll
    Project[] projects = restTemplate.getForObject(url + "/api/project/findAll", Project[].class);
    if (projects.length == 0) {
      fail("Failed to get all projects");
    }

  }



}
