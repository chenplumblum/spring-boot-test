package com.plumblum.rabbitmq.ThemePattern;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Auther: cpb
 * @Date: 2018/11/16 16:40
 * @Description:
 */
@Configuration
public class RabbitMQQueueConfig {
    final static String message = "topic.A";
    final static String messages = "topic.B";


    @Bean
    public Queue queueMessage() {
        return new Queue(RabbitMQQueueConfig.message);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(RabbitMQQueueConfig.messages);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}
