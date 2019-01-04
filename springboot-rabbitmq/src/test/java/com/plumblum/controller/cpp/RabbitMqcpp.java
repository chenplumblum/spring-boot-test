package com.plumblum.controller.cpp;

import com.plumblum.controller.CPP.ConsumerSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: cpb
 * @Date: 2018/11/16 17:03
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMqcpp {
    @Autowired
    private ConsumerSender consumerSenders;

    @Test
    public void oneToMany() throws Exception {
        for (int i=0;i<100;i++){
            // Thread.sleep(10);
            consumerSenders.send(i);
        }
    }
}
