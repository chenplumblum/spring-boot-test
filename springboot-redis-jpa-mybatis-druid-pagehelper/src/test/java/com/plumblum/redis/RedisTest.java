package com.plumblum.redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: cpb
 * @Date: 2018/8/3 17:10
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void redistest() {

        // 保存字符串
        stringRedisTemplate.opsForValue().set("admin", "111");
        System.out.println(redisTemplate.opsForValue().get("admin"));

        // 保存对象
//        User user = new User("超人", 20);
//
//        System.out.println(user);
//        redisTemplate.opsForValue().set(user.getUsername(), user,12, TimeUnit.HOURS);
//
//        user = new User("蝙蝠侠", 30);
//        redisTemplate.opsForValue().set(user.getUsername(), user,12, TimeUnit.HOURS);
//
//        user = new User("蜘蛛侠", 40);
//        redisTemplate.opsForValue().set(user.getUsername(), user,12, TimeUnit.HOURS);
//
//        System.out.println(redisTemplate.opsForValue().get(user.getUsername()));


    }
}

