package com.plumblum.applicationevent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created with IDEA
 * author:plumblum
 * Date:2018/9/1
 * Time:20:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationEvent {

    @Resource
    private DemoPublisher demoPublisher;

    @Test
    public void event(){
        demoPublisher.publish("你好吗");

    }
}
