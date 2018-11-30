package com.plumblum.repository;

import com.plumblum.entity.User;
import com.plumblum.shiro.PasswordHelper;
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
    public void findByName(){
        userRepository.findByName("zhang");
    }

    @Test
    public void add(){
        User usersEntity = new User();
        usersEntity.setId(1);
        usersEntity.setUsername("admin");
        usersEntity.setPassword("admin");
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(usersEntity);
        userRepository.save(usersEntity);
    }

}
