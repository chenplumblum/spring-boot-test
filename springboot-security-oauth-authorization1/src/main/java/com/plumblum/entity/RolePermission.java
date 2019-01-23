package com.plumblum.entity;

import javax.persistence.*;

/**
 * @Auther: cpb
 * @Date: 2018/8/10 10:17
 * @Description:
 */
@Entity
@Table(name = "sys_role_permission", schema = "shiro", catalog = "")
public class RolePermission {

    private Long roleId;
    private Long permissionId;
    @Id
    private long rolePermissionId;

    @Basic
    @Column(name = "role_id")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "permission_id")
    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Basic
    @Column(name = "role_permission_id")
    public long getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(long rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolePermission that = (RolePermission) o;

        if (rolePermissionId != that.rolePermissionId) return false;
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;
        if (permissionId != null ? !permissionId.equals(that.permissionId) : that.permissionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (permissionId != null ? permissionId.hashCode() : 0);
        result = 31 * result + (int) (rolePermissionId ^ (rolePermissionId >>> 32));
        return result;
    }
}
