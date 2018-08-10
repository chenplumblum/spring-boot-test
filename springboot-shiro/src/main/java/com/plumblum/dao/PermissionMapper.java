package com.plumblum.dao;


import com.plumblum.entity.Permission;

import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2018/8/2 09:46
 * @Description:
 */
public interface PermissionMapper {
    List<Permission> findPermissions(String userName);

}
