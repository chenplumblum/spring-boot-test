package com.plumblum.controller.CPP;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: cpb
 * @Date: 2018/11/16 16:10
 * @Description:
 */
@Component
@RabbitListener(queues = "two")
public class ProducerReceiverTwo {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver2  : " + hello);
    }

}
