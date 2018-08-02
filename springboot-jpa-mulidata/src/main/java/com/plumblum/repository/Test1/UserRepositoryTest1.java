package com.plumblum.repository.Test1;

import com.plumblum.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: cpb
 * @Date: 2018/8/2 11:23
 * @Description:
 */
public interface UserRepositoryTest1 extends JpaRepository<UsersEntity,Long> {

}
