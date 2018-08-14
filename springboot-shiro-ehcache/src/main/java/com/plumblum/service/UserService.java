package com.plumblum.service;

import com.plumblum.entity.Permission;
import com.plumblum.entity.Role;
import com.plumblum.entity.User;

import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2018/8/10 10:39
 * @Description:
 */
public interface UserService {
    User findByName(String userName);

    List<String> findPermissions(String userName);

    List<String> findRoles(String userName);

}
