package com.plumblum.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: cpb
 * @Date: 2018/8/17 14:22
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void string(){
        //set(name,value,time,timeUnit,offset(偏移量))

        User user = new User("你好",20);
        redisTemplate.opsForValue().set(user.getUsername(),user);
        System.out.println(redisTemplate.opsForValue().get(user.getUsername()));

        redisTemplate.opsForValue().set(user.getUsername(),user,4,TimeUnit.HOURS);
        System.out.println(redisTemplate.opsForValue().get(user.getUsername()));

        String name = "increment";
        Long  i = 10L;
        redisTemplate.opsForValue().set(name,i);
        redisTemplate.opsForValue().increment(name,i);





    }

    @Test
    public void list(){
        String name = "my:listRight";
        ArrayList<User> list = new ArrayList<>();
        User user1 = new User("你好",20);
        User user2 = new User("谢谢",23);
        list.add(user1);
        list.add(user2);
        redisTemplate.opsForList().rightPushAll(name,user1);
        redisTemplate.opsForList().rightPushAll(name,user2);
        redisTemplate.opsForList().leftPop(name);
        System.out.println(redisTemplate.opsForList().range(name, 0, -1));

    }

    @Test
    public void map(){
        String name = "my:map";

    }
}
