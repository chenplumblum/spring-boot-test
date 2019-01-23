package com.plumblum.service;

import com.plumblum.entity.UsersEntity;
import com.plumblum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2019/1/10 14:55
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UsersEntity> findAll(){
        return userRepository.findAll();
    }

    public UsersEntity findOne(long userid){
        return userRepository.getOne(userid);
    }

}
