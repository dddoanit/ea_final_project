package edu.mum.cs544.project;

import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import edu.mum.cs544.project.model.Skill;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinalProjectApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SkillRestControllerTest {

  @LocalServerPort
  private int port;

  private RestTemplate restTemplate = new RestTemplate();
  private String url = "http://localhost:8080/api/skill";

  @Before
  public void setup() {
    url = "http://localhost:" + port;
  }

  @Test
  public void test() {
    // Test insert
    Skill skill = new Skill();
    skill.setName("Database");
    Skill savedSkill = restTemplate.postForObject(url + "/api/skill/save", skill, Skill.class);;
    if (savedSkill.getId() == 0) {
      fail("Failed to insert a new skill");
    }


    // test delete
    restTemplate.postForObject(url + "/api/skill/delete/" + savedSkill.getId(), null, Skill.class);
    Skill deletedSkill =
        restTemplate.getForObject(url + "/api/skill/findById/" + savedSkill.getId(), Skill.class);
    if (deletedSkill != null) {
      fail("Failed to delete skill");
    }

    // findAll
    Skill[] skills = restTemplate.getForObject(url + "/api/skill/findAll", Skill[].class);
    if (skills.length == 0) {
      fail("Failed to get all skills");
    }

  }



}
