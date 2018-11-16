package com.plumblum.rabbitmq.topic;

import com.plumblum.rabbitmq.ThemePattern.TopicSend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: cpb
 * @Date: 2018/11/16 17:34
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopciTest {
    @Autowired
    private TopicSend sender;

    @Test
    public void topic() throws Exception {
        sender.send();
    }

    @Test
    public void topic1() throws Exception {
        sender.send1();
    }

    @Test
    public void topic2() throws Exception {
        sender.send2();
    }
}
