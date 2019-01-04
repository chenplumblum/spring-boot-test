package com.plumblum.controller.Publishsubscribe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: cpb
 * @Date: 2018/11/16 17:27
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQ {
    @Autowired
    private ConsumerSender fanoutSender;

    @Test
    public void setFanoutSender(){
        fanoutSender.send();
    }
}
