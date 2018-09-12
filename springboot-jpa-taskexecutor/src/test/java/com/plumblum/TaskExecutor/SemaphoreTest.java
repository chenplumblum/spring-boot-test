package com.plumblum.TaskExecutor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created with IDEA
 * author:plumblum
 * Date:2018/9/9
 * Time:11:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SemaphoreTest {
    @Resource
    private SemaphoreDemo semaphoreDemo;



    @Test
    public void testOne(){

        // 只能允许2个客户端线程访问服务器资源
        final Semaphore semaphore = new Semaphore(2);

        for (int i= 0 ;i<20 ;i++){
            semaphoreDemo.testTwo(i);
//            semaphoreDemo.testOne(i);
        }
    }









}
