package com.plumblum.TaskExecutor;

import jdk.nashorn.internal.runtime.ECMAException;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

/**
 * Created with IDEA
 * author:plumblum
 * Date:2018/9/9
 * Time:22:27
 */
@Component
public class CyclicBarrierDemo {
    @Resource
    private TaskExecutor executor;

    CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    @Async
    public void testOne(int name){
        try{
            System.out.println("用户："+name+"到达聚餐地点，当前数目"+cyclicBarrier.getNumberWaiting()+1);
//            设置阻塞点
            cyclicBarrier.await();
            System.out.println("人员到齐，活动开始");
            System.out.println("活动结束用户："+name+"离开");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

