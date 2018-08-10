package com.plumblum.repository;

import com.plumblum.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: cpb
 * @Date: 2018/8/7 20:07
 * @Description:
 */
public interface RolePermissionRepository extends JpaRepository<RolePermission,Long> {
}
