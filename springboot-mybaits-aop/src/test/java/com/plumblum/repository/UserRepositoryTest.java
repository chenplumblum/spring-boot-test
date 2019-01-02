package com.plumblum.repository;

import com.plumblum.entity.UsersEntity;
import com.plumblum.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Auther: cpb
 * @Date: 2018/8/2 11:28
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void userTest(){
        System.out.println(userMapper.getAll());
    }
}
