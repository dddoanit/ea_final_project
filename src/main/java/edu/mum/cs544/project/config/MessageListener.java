/**
* Author: DatDoan
* Created Date: Apr 21, 2018
*/
package edu.mum.cs544.project.config;

import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
  public void receiveMessage(Map<String, String> message) {
    System.out.println("Received <" + message + ">");
  }
}
