/**
* Author: DatDoan
* Created Date: Apr 21, 2018
*/
package edu.mum.cs544.project.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
  public static final String MESSAGE_QUEUE = "email-queue";
  private static final String TOPIC_EXCHANGE = "email-exchange";
  
  @Bean
  Queue queue() {
      return new Queue(MESSAGE_QUEUE, false);
  }
  
  @Bean
  TopicExchange exchange() {
      return new TopicExchange(TOPIC_EXCHANGE);
  }
  
  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
      return BindingBuilder.bind(queue).to(exchange).with(MESSAGE_QUEUE);
  }
  
  @Bean
  MessageListenerAdapter listenerAdapter(MessageListener receiver) {
      return new MessageListenerAdapter(receiver, "receiveMessage");
  }
  
  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
  MessageListenerAdapter listenerAdapter) {
      SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
      container.setConnectionFactory(connectionFactory);
      container.setQueueNames(MESSAGE_QUEUE);
      container.setMessageListener(listenerAdapter);
      return container;
  }
}
