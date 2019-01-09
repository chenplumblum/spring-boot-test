package com.plumblum.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2019/1/9 15:41
 * @Description:
 */
@Mapper
public interface RoleDao {
    List<String> findRoles(String userName);
}
