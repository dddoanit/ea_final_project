package edu.mum.cs544.project;

import static org.assertj.core.api.Assertions.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import edu.mum.cs544.project.FinalProjectApplication;
import edu.mum.cs544.project.model.User;
import edu.mum.cs544.project.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinalProjectApplication.class)
public class UserTests {

  @Autowired
  private UserService userService;
  
  @Test
  public void test() {
    
    // Test insert
    User user = new User();
    user.setName("Test");
    user.setEmail("test@mum.edu");
    user.setPassword("pwd");
    User savedUser = userService.save(user);
    if(savedUser.getId() == 0) {
      fail("Failed to insert a new user");
    }
    
    // test delete
    userService.delete(user.getId());
    User deletedUser = userService.findById(user.getId());
    if (deletedUser != null) {
      fail("Failed to delete user");
    }
    
  }

}
