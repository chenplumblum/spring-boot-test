package com.plumblum.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void hash(){
        String name = "my:hash";
        Map<String,Object> testMap = new HashMap();
        testMap.put("name","jack");
        testMap.put("age",27);
        testMap.put("class","1");
        redisTemplate.opsForHash().putAll(name,testMap);
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
    public void set(){
        String name = "my:set";
        String[] starArrays = new String[]{"star1","star2","star3"};
        //放入会去重
        redisTemplate.opsForSet().add(name,starArrays);
        //是否包含member
        System.out.println(redisTemplate.opsForSet().isMember(name, "star2"));
        //包含的所有member
        System.out.println(redisTemplate.opsForSet().members(name));
        //随机获取value
        System.out.println(redisTemplate.opsForSet().randomMembers(name, 2));
    }
    @Test
    public void zSet(){
        String name = "my:zset";
        String[] arrays = new String[]{};
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple("name1",1.0);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple("name1",2.0);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple("name2",2.0);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        redisTemplate.opsForZSet().add(name,tuples);
    }

}
