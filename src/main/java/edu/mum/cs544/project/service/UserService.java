package edu.mum.cs544.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.mum.cs544.project.config.RabbitMqConfig;
import edu.mum.cs544.project.model.Project;
import edu.mum.cs544.project.model.User;
import edu.mum.cs544.project.repository.UserRepository;

@Service
@Transactional
public class UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);
  
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

  @Async("asyncExecutor")
  public void sendProjectMessage(int skillId, Project project) {
    logger.info("sendProjectMessage with Thread " + Thread.currentThread().getName());
    List<User> users = userRepository.findBySkill(skillId);

    for (User user : users) {
        Map<String, String> map = new HashMap<>();
        map.put("email_to", user.getEmail());
        map.put("email_title", project.getName());
        map.put("email_content", project.getDescription());
        rabbitTemplate.convertAndSend(RabbitMqConfig.MESSAGE_QUEUE, map);
  	}
  }

  @Async("asyncExecutor")
  public void sendToAdmin(User u) {
    logger.info("sendToAdmin with Thread " + Thread.currentThread().getName());
    List<User> users = userRepository.findAdmin();
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

    map.put("email_title", "New Volunteer Registered! ");
    map.put("email_content", "Hello! My Name is: "+ u.getName()+
    " I am newly registered volunteer, can you"
    + "please assign me to a project? You can reach me through " + u.getEmail());

    rabbitTemplate.convertAndSend(RabbitMqConfig.MESSAGE_QUEUE, map);
  }

}
