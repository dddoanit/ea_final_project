/**
 * Author: DatDoan Created Date: Apr 21, 2018
 */


package edu.mum.cs544.project.config;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Author: Lwin Moe Aung Modified Date: Apr 22, 2018 Comment: Modified receiveMessage() to send
 * email.
 */

@Component
public class MessageListener {

  @Autowired
  public JavaMailSender emailSender;

  public void receiveMessage(Map<String, String> message) {
    System.out.println("Received " + message);
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
