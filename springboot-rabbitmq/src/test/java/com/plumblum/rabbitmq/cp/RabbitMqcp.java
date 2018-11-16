package com.plumblum.rabbitmq.cp;

import com.plumblum.rabbitmq.CP.ConsumerSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: cpb
 * @Date: 2018/11/16 16:35
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMqcp {
    @Autowired
    private ConsumerSender consumerSenders;

    @Test
    public void hello() throws Exception {
        consumerSenders.send();
    }
}
