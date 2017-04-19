package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/4/17.
 */
@Entity
@Table(name = "sys_bt_users_roles", schema = "agile_db", catalog = "")
public class SysBtUsersRolesEntity {
    private int roleId;
    private int userId;
    private int sysBtUsersRolesId;

    @Basic
    @Column(name = "ROLE_ID")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "USER_ID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "SYS_BT_USERS_ROLES_ID")
    public int getSysBtUsersRolesId() {
        return sysBtUsersRolesId;
    }

    public void setSysBtUsersRolesId(int sysBtUsersRolesId) {
        this.sysBtUsersRolesId = sysBtUsersRolesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysBtUsersRolesEntity that = (SysBtUsersRolesEntity) o;

        if (roleId != that.roleId) return false;
        if (userId != that.userId) return false;
        if (sysBtUsersRolesId != that.sysBtUsersRolesId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + userId;
        result = 31 * result + sysBtUsersRolesId;
        return result;
    }
}
