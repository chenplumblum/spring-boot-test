package com.plumblum.controller;

import com.plumblum.entity.UsersEntity;
import com.plumblum.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2018/8/28 09:26
 * @Description:
 */
@RestController
@RequestMapping("")
public class UserController {
    @Resource
    private UserRepository userRepository;

    @RequestMapping("/hello/*")
    public List<UsersEntity> getAll(){
       return userRepository.findAll();
    }

    @RequestMapping("/*")
    public UsersEntity getOne(){
        return userRepository.getOne(1l);
    }
}
