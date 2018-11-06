package com.plumblum.redis.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.*;
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
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private JedisPool jedisPool;

    @Test
    public void testString(){
        Jedis jedis = jedisPool.getResource();
        jedis.set("test","test");
        System.out.println(jedis.get("test"));
    }
    @Test
    public void string() {
        //set(name,value,time,timeUnit,offset(偏移量))

//      存对象
        User user = new User("你好", 20);
        redisTemplate.opsForValue().set(user.getUsername(), user);
//      对象+过期时间
        redisTemplate.opsForValue().set(user.getUsername(), user, 4, TimeUnit.HOURS);

//      long
        String name = "increment";
        Long i = 10L;
        redisTemplate.opsForValue().set(name, i);
//      给定自增量
        redisTemplate.opsForValue().increment(name, i);
//      给定自减量
        redisTemplate.opsForValue().increment(name, -i);
        redisTemplate.opsForValue().increment("name",1);

//      返回value长度
        System.out.println(redisTemplate.opsForValue().size(name));

//      string
        String name1 = "string";
        String value1 = "string";
        redisTemplate.opsForValue().set(name1,value1);
//        APPEND key value
        redisTemplate.opsForValue().append(name1," 你好");

//        setIfAbsent（是否存在key或value）




    }

    @Test
    public void list() {
        String name = "my:listTest";
        ArrayList<User> list = new ArrayList<>();
        User user1 = new User("你好吗", 20);
        User user2 = new User("谢谢你", 23);
        list.add(user1);
        list.add(user2);
        redisTemplate.opsForList().rightPushAll(name, user1);
        redisTemplate.opsForList().rightPushAll(name, user2);
        redisTemplate.opsForList().leftPop(name);
        System.out.println(redisTemplate.opsForList().range(name, 0, -1));
        System.out.println(redisTemplate.opsForList().size(name));

    }

    @Test
    public void hash() {
        String name = "my:hash";
        Map<String, Object> testMap = new HashMap();
        testMap.put("name", "jack");
        testMap.put("age", 27);
        testMap.put("class", "1");
        redisTemplate.opsForHash().putAll(name, testMap);
        //删除某个键值
        System.out.println(redisTemplate.opsForHash().delete(name, "name"));
        //得到某个键值
        System.out.println(redisTemplate.opsForHash().get(name, "age"));
        //得到整个键值
        System.out.println(redisTemplate.opsForHash().entries(name));
//        得到键值的key
        System.out.println(redisTemplate.opsForHash().keys(name));


    }

    @Test
    public void set() {
        String name = "my:set";
        String[] starArrays = new String[]{"star1", "star2", "star3"};
        //放入会去重
        redisTemplate.opsForSet().add(name, starArrays);
        //是否包含member
        System.out.println(redisTemplate.opsForSet().isMember(name, "star2"));
        //包含的所有member
        System.out.println(redisTemplate.opsForSet().members(name));
        //随机获取value
        System.out.println(redisTemplate.opsForSet().randomMembers(name, 2));
    }

    @Test
    public void zSet() {
        String name = "my:zset";
        String[] arrays = new String[]{};
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple("name1", 1.0);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple("name1", 2.0);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple("name2", 2.0);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        redisTemplate.opsForZSet().add(name, tuples);
    }

    //    HyperLoglog
    @Test
    public void hyperLogLog() {
        HyperLogLogOperations<String, Object> hyperLogLogOperations = redisTemplate.opsForHyperLogLog();
        hyperLogLogOperations.add("book", "a", "b", "c", "d");
        hyperLogLogOperations.add("bag", "a", "e", "d");
        hyperLogLogOperations.add("del", "f", "g", "h");

        System.out.println(hyperLogLogOperations.size("book"));
        System.out.println(hyperLogLogOperations.size("bag"));

        hyperLogLogOperations.delete("del");
        System.out.println(hyperLogLogOperations.size("book", "bag", "del"));
        hyperLogLogOperations.union("total", "book", "bag", "del");
        System.out.println(hyperLogLogOperations.size("total"));
    }


}