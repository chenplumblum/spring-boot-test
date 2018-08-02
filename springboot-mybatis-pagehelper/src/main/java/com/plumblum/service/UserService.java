package com.plumblum.service;

import com.plumblum.entity.UserEntity;

import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2018/8/2 09:54
 * @Description:
 */
public interface UserService {
    List<UserEntity> getAll();
}
