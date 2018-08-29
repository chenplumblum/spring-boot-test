package com.plumblum.service;

import com.plumblum.entity.UsersEntity;
import com.plumblum.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: cpb
 * @Date: 2018/8/29 19:06
 * @Description:
 */
@Service("userService")
public class UserService {

    @Resource
    private UserRepository userRepository;

    public void insert(){
        UsersEntity user  = new UsersEntity();

        try {
            userRepository.save(user);
        }catch (RuntimeException e){

        }finally{

        }
    }
}
