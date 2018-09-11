package com.plumblum.TaskExecutor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.CyclicBarrier;

/**
 * Created with IDEA
 * author:plumblum
 * Date:2018/9/9
 * Time:22:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CyclicBarrierTest{
    @Resource
    private CyclicBarrierDemo cyclicBarrierDemo;

    @Test
    public void test(){
//        设置阻塞数目
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 0; i<3;i++){
            cyclicBarrierDemo.testOne(i);
        }
    }

}
