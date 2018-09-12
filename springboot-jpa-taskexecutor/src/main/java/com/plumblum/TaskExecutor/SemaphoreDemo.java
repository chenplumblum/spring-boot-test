package com.plumblum.TaskExecutor;

import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Semaphore;

/**
 * Created with IDEA
 * author:plumblum
 * Date:2018/9/9
 * Time:11:02
 */
@Component
public class SemaphoreDemo {



    @Resource
    private TaskExecutor taskExecutor;
    //定义窗口个数（既可以同时运行几个线程）
    Semaphore semaphore = new Semaphore(2);

    public void testOne(int name){
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try{
//                    获取线程锁
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+" "+"用户（"+name+")进入窗口，准备买票");
                System.out.println(Thread.currentThread().getName()+"用户（"+name+")买票完成，即将离开");
                System.out.println(Thread.currentThread().getName()+"用户（"+name+"）离开售票窗口");
//                    释放线程锁
                semaphore.release();

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }

    @Async
    public void testTwo(int name){
        try{
//          获取线程锁
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+" "+"用户（"+name+")进入窗口，准备买票");
            System.out.println(Thread.currentThread().getName()+"用户（"+name+")买票完成，即将离开");
            System.out.println(Thread.currentThread().getName()+"用户（"+name+"）离开售票窗口");
//          释放线程锁
            semaphore.release();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
