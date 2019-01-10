package com.plumblum.repository;

import com.plumblum.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Auther: cpb
 * @Date: 2018/8/7 20:07
 * @Description:
 */
public interface UserRepository extends JpaRepository<SysUser,Long>{
    @Query("select t from SysUser t where t.username = ?1")
    SysUser findByName(String userName);
}
