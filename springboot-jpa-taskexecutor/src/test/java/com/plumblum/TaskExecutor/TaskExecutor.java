package com.plumblum.TaskExecutor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Auther: cpb
 * @Date: 2018/8/31 13:51
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskExecutor {

    @Resource
    private Hello hello;

    @Test
    public void testHello(){
        for(int i = 0;i<10;i++){
        hello.hello("你好");
        }
    }
}
