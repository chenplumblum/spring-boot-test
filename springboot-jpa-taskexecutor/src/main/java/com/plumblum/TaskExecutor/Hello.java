package com.plumblum.TaskExecutor;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Auther: cpb
 * @Date: 2018/8/31 13:55
 * @Description:
 */
@Component
public class Hello {
    @Async
    public void hello(String name){
        LoggerFactory.getLogger(Hello.class).info(name + ":Hello World!");
    }
}
