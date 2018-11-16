package com.plumblum.rabbitmq.Publishsubscribe;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: cpb
 * @Date: 2018/11/16 17:21
 * @Description:
 */
@Component
@RabbitListener(queues = "fanout.A")
public class FanoutReceiveA {


    @RabbitHandler
    public void process(String message){
        System.out.println("fanout.A:"+message);
    }

}
