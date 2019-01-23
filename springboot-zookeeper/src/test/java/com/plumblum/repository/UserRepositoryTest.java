package com.plumblum.repository;

import com.plumblum.entity.UsersEntity;
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
    private UserRepository userRepository;

    @Test
    public void add(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(123);
        usersEntity.setUserName("123");
        usersEntity.setPassWord("123");
        usersEntity.setNickName("123");
        usersEntity.setUserSex("男");
        userRepository.save(usersEntity);
    }

    @Test
    public void update(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(123);
        usersEntity.setUserName("test");
        usersEntity.setPassWord("tes");
        usersEntity.setNickName("tes");
        usersEntity.setUserSex("女");
        userRepository.save(usersEntity);
    }

    @Test
    public void find(){
        userRepository.findAll();
    }

    @Test
    public void delete(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(123);
        userRepository.delete(usersEntity);
    }
}
