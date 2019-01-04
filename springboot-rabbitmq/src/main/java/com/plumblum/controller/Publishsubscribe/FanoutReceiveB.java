package com.plumblum.controller.Publishsubscribe;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: cpb
 * @Date: 2018/11/16 17:21
 * @Description:
 */
@Component
@RabbitListener(queues = "fanout.B")
public class FanoutReceiveB {


    @RabbitHandler
    public void process(String message){
        System.out.println("fanout.B:"+message);
    }

}
