package com.plumblum.repository;

import com.plumblum.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2018/8/2 11:23
 * @Description:
 */
public interface UserRepository extends JpaRepository<UsersEntity,Long> {

}
