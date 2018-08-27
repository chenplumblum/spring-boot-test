package com.plumblum.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: cpb
 * @Date: 2018/8/22 16:55
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringRedisTemplateTest  {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


}
