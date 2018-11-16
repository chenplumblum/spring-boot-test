package com.plumblum.rabbitmq.CPP;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * @Auther: cpb
 * @Date: 2018/11/16 16:18
 * @Description:
 */
@Controller
public class ConsumerSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        rabbitTemplate.convertAndSend("hello", context);
    }

    public void send(int i) {
        String context = "spirng boot  queue"+" ****** "+i;
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("neo", context);
    }
}
