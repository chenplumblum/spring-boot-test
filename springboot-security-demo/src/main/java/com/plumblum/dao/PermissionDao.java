package com.plumblum.dao;

import com.plumblum.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2019/1/9 15:41
 * @Description:
 */
@Mapper
public interface PermissionDao {
    List<String> findPermissions(String userName);
    public List<SysPermission> findAll();
    public List<SysPermission> findByAdminUserId(long userId);
}
