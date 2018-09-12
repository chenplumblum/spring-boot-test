package com.plumblum.TaskExecutor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IDEA
 * author:plumblum
 * Date:2018/9/9
 * Time:23:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReentranLockTest {


    @Resource
    private SynchronizedDemo synchronizedDemo;


    @Test
    public void test(){
        final  int j = 0;
        for(int i = 0; i<30;i++){
            synchronizedDemo.testOne(j);
        }
    }
}
