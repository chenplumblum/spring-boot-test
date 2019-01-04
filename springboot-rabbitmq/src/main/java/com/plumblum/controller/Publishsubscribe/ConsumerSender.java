package com.plumblum.controller.Publishsubscribe;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: cpb
 * @Date: 2018/11/16 17:18
 * @Description:
 */
@Component
public class ConsumerSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hi, fanout msg ";
        System.out.println("Sender : " + context);
        rabbitTemplate.convertAndSend("fanoutExchange","", context);
    }
}
