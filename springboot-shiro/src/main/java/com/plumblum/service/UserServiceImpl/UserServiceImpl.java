package com.plumblum.service.UserServiceImpl;

import com.plumblum.dao.PermissionMapper;
import com.plumblum.dao.RoleMapper;
import com.plumblum.entity.Permission;
import com.plumblum.entity.Role;
import com.plumblum.entity.User;
import com.plumblum.repository.UserRepository;
import com.plumblum.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2018/8/10 10:39
 * @Description:
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    @Resource
    private UserRepository userRepository;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RoleMapper roleMapper;


    @Override
    public User findByName(String userName) {
        return userRepository.findByName(userName);
    }

    @Override
    public List<String> findPermissions(String userName) {
        return permissionMapper.findPermissions(userName);
    }

    @Override
    public List<String> findRoles(String userName) {
        return roleMapper.findRoles(userName);
    }
}
