package com.plumblum.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Auther: cpb
 * @Date: 2018/11/5 14:26
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private RedisTemplate<String,Object> template;
    @Test
    public void set(){
      Jedis jedis =   jedisPool.getResource();
      jedis.set("test","test");
      System.out.println(jedis.get("test"));
    }

    @Test
    public void springTest(){
        template.opsForValue().set("hello","hello");
        System.out.println(template.opsForValue().get("hello"));
    }



}
