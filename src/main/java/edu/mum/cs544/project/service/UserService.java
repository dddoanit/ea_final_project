package edu.mum.cs544.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.mum.cs544.project.config.RabbitMqConfig;
import edu.mum.cs544.project.model.Project;
import edu.mum.cs544.project.model.Role;
import edu.mum.cs544.project.model.User;
import edu.mum.cs544.project.repository.UserRepository;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  public User save(User user) {
    return userRepository.save(user);
  }

  public void delete(long id) {
    User user = userRepository.findOne(id);
    user.clearRoles();
    userRepository.delete(user);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(long id) {
    return userRepository.findOne(id);
  }

  public User findByEmail(String email) {
    List<User> users = userRepository.findByEmailAllIgnoreCase(email);
    if (users.size() == 1) {
      return users.get(0);
    }
    return null;
  }

  public List<User> findBySkill(int skillId) {
    return userRepository.findBySkill(skillId);
  }

  public void sendProjectMessage(int skillId, Project project) {
    List<User> users = userRepository.findBySkill(skillId);
    int i = 0;
    String recipients = "";

    for (User user : users) {
      if (i != users.size() - 1) {
        recipients += user.getEmail() + ", ";
      } else {
        recipients += user.getEmail();
      }
      i++;
    }
    Map<String, String> map = new HashMap<>();
    map.put("email_to", recipients);
    map.put("email_title", project.getName());
    map.put("email_content", project.getDescription());
    rabbitTemplate.convertAndSend(RabbitMqConfig.MESSAGE_QUEUE, map);
  }

  public void sendToAdmin() {
    List<User> users = userRepository.findAll();
    int i = 0;
    String recipients = "";
    for (User user : users) {
      if (i != users.size() - 1) {
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
          if (role.getName().equals("ADMIN")) {
            recipients += user.getEmail() + ", ";

          }
        }
      } else {
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
          if (role.getName().equals("ADMIN")) {
            recipients += user.getEmail();

          }
        }

      }
    }
    Map<String, String> map = new HashMap<>();
    map.put("email_to", recipients);
    map.put("email_title", "New User Registered");
    map.put("email_content", "I am New, Please Assign me to some Project");
    rabbitTemplate.convertAndSend(RabbitMqConfig.MESSAGE_QUEUE, map);
  }

}
