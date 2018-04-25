/**
 * Author: DatDoan Created Date: Apr 21, 2018
 */


package edu.mum.cs544.project.config;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import edu.mum.cs544.project.service.UserService;

/**
 * Author: Lwin Moe Aung Modified Date: Apr 22, 2018 Comment: Modified receiveMessage() to send
 * email.
 */

@Component
public class MessageListener {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);
  
  @Autowired
  public JavaMailSender emailSender;

  public void receiveMessage(Map<String, String> message) {
    logger.info("Received " + message);
    asyncEmail(message);
  }
  
  @Async
  public void asyncEmail(Map<String, String> message) {
	  SimpleMailMessage msg = new SimpleMailMessage();
	    try {
	      msg.setTo(message.get("email_to").split(","));
	      msg.setSubject(message.get("email_title"));
	      msg.setText(message.get("email_content"));
	      emailSender.send(msg);
	    } catch (MailException exception) {
	      exception.printStackTrace();
	    }
  }
}
