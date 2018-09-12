package com.plumblum.applicationevent;


import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created with IDEA
 * author:plumblum
 * Date:2018/9/1
 * Time:19:57
 */
@Component
public class DemoPublisher {

    @Resource
    private ApplicationContext applicationContext;

    public void publish(String message){
        applicationContext.publishEvent(new DemoEvent(this,message));
    }
}
