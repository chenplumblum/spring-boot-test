package com.plumblum.dao;

import com.plumblum.model.UserEntity;

import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2018/8/2 09:46
 * @Description:
 */
public interface UserMapper {
    List<UserEntity> getAll();

}
