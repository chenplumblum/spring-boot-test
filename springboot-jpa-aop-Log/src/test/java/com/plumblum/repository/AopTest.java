package com.plumblum.repository;

import com.plumblum.entity.UsersEntity;
import com.plumblum.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Auther: cpb
 * @Date: 2018/8/30 09:56
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AopTest {
    @Resource
    private UserService userService;

    @Test
    public void save(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(11);
        usersEntity.setUserName("124");
        usersEntity.setPassWord("124");
        usersEntity.setNickName("124");
        usersEntity.setUserSex("nan");
        userService.insert(usersEntity);

    }
}
