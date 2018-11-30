package com.plumblum.entity;

import javax.persistence.*;

/**
 * @Auther: cpb
 * @Date: 2018/8/10 10:17
 * @Description:
 */
@Entity
@Table(name = "sys_permission", schema = "shiro", catalog = "")
public class Permission {
    @Id
    private long id;
    private String permission;
    private String description;
    private Byte available;

    @Basic
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "permission")
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "available")
    public Byte getAvailable() {
        return available;
    }

    public void setAvailable(Byte available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        if (id != that.id) return false;
        if (permission != null ? !permission.equals(that.permission) : that.permission != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (available != null ? !available.equals(that.available) : that.available != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        return result;
    }
}
