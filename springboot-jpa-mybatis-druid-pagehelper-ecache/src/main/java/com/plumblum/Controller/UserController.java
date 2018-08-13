package com.plumblum.Controller;

import com.plumblum.dao.UserMapper;
import com.plumblum.entity.UsersEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserMapper userMapper;

    @Cacheable(value = "plumblum")
    @RequestMapping("/userAll")
    public List<UsersEntity> getAll(){
        return  userMapper.getAll();
    }
}
