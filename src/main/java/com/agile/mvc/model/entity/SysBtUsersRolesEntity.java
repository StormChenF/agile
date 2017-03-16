package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/3/16.
 */
@Entity
@Table(name = "sys_bt_users_roles", schema = "agile_db", catalog = "")
public class SysBtUsersRolesEntity {
    private int id;
    private int roleId;
    private int userId;

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ROLE_ID", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "USER_ID", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysBtUsersRolesEntity that = (SysBtUsersRolesEntity) o;

        if (id != that.id) return false;
        if (roleId != that.roleId) return false;
        if (userId != that.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + roleId;
        result = 31 * result + userId;
        return result;
    }
}
