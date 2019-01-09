package com.plumblum.entity;

import javax.persistence.*;

/**
 * @Auther: cpb
 * @Date: 2018/8/10 10:17
 * @Description:
 */
@Entity
@Table(name = "sys_user_role", schema = "shiro", catalog = "")
public class UserRole {

    private Long userId;
    private Long roleId;
    @Id
    private long userRoleId;

    @Basic
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "role_id")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "user_role_id")
    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (userRoleId != userRole.userRoleId) return false;
        if (userId != null ? !userId.equals(userRole.userId) : userRole.userId != null) return false;
        if (roleId != null ? !roleId.equals(userRole.roleId) : userRole.roleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        result = 31 * result + (int) (userRoleId ^ (userRoleId >>> 32));
        return result;
    }
}
