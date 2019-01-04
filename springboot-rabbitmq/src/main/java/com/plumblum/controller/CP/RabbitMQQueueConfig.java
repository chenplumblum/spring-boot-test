package com.plumblum.controller.CP;


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
    @Bean
    public Queue queueOne() {
        return new Queue("one");
    }

}
