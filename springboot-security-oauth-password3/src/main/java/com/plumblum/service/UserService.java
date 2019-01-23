package com.plumblum.service;

import com.plumblum.dao.PermissionDao;
import com.plumblum.dao.RoleDao;
import com.plumblum.entity.SysPermission;
import com.plumblum.entity.SysRole;
import com.plumblum.entity.SysUser;
import com.plumblum.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2019/1/9 16:48
 * @Description:
 */
@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private PermissionDao permissionDao;

    @Resource
    private RoleDao roleDao;

    public SysUser findByName(String userName) {
        return userRepository.findByName(userName);
    }


    public List<String> findPermissions(String userName) {
        return permissionDao.findPermissions(userName);
    }


    public List<String> findRoles(String userName) {
        return roleDao.findRoles(userName);
    }

    public List<SysPermission> findAll(){
        return permissionDao.findAll();
    }
    public List<SysPermission> findPermissionByUserId(long userId){
        return permissionDao.findPermissionByUserId(userId);
    }

    public List<SysRole> findRoleByUserId(long userId){
        return roleDao.findRoleByUserId(userId);
    }
}
