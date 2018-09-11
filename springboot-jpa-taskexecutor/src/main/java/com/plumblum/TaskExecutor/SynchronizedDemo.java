package com.plumblum.TaskExecutor;


import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IDEA
 * author:plumblum
 * Date:2018/9/9
 * Time:22:54
 */
@Component
public class SynchronizedDemo {

    private ReentrantLock reentrantLock;

    private int test;

    @Resource
    private TaskExecutor executor;


    @Async
    public void testOne(int name){
        try{
            reentrantLock.lock();
            System.out.println(test++);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            reentrantLock.unlock();
        }
    }
}
