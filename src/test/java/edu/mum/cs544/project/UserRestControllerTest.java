package edu.mum.cs544.project;

import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import edu.mum.cs544.project.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinalProjectApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRestControllerTest {

  @LocalServerPort
  private int port;

  private RestTemplate restTemplate = new RestTemplate();
  private String url = "http://localhost:8080/api/user";

  @Before
  public void setup() {
    url = "http://localhost:" + port;
  }

  @Test
  public void test() {
    // Test insert
    User user = new User();
    user.setName("Test01");
    user.setEmail("test01@mum.edu");
    user.setPassword("pwd");
    User savedUser = restTemplate.postForObject(url + "/api/user/save", user, User.class);;
    if (savedUser.getId() == 0) {
      fail("Failed to insert a new user");
    }


    // test delete
    restTemplate.postForObject(url + "/api/user/delete/" + savedUser.getId(), null, User.class);
    User deletedUser =
        restTemplate.getForObject(url + "/api/user/findById/" + savedUser.getId(), User.class);
    if (deletedUser != null) {
      fail("Failed to delete user");
    }

    // findAll
    User[] users = restTemplate.getForObject(url + "/api/user/findAll", User[].class);
    if (users.length == 0) {
      fail("Failed to get all users");
    }

  }



}
