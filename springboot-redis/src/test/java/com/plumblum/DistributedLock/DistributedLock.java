package com.plumblum.DistributedLock;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: cpb
 * @Date: 2018/11/19 15:14
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributedLock {

    @Autowired
    private DistributedLock distributedLock;



}
