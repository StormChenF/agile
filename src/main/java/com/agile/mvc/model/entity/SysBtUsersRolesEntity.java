package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_bt_users_roles",  catalog = "agile_db")
public class SysBtUsersRolesEntity implements Serializable {

    //唯一标识
    private Integer sysBtUsersRolesId;
    //角色唯一标识
    private Integer roleId;
    //用户唯一标识
    private Integer userId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_BT_USERS_ROLES_ID")
    public Integer getSysBtUsersRolesId() {
        return sysBtUsersRolesId;
    }

    public void setsysBtUsersRolesId(int sysBtUsersRolesId) {
        this.sysBtUsersRolesId = sysBtUsersRolesId;
    }

    @Basic
    @Column(name = "ROLE_ID")
    public Integer getRoleId() {
        return roleId;
    }

    public void setroleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "USER_ID")
    public Integer getUserId() {
        return userId;
    }

    public void setuserId(int userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysBtUsersRolesEntity that = (SysBtUsersRolesEntity) o;

        if (sysBtUsersRolesId != that.sysBtUsersRolesId) return false;
        if (roleId != that.roleId) return false;
        if (userId != that.userId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + sysBtUsersRolesId;
        result = 31 * result + roleId;
        result = 31 * result + userId;
        return result;
    }
}
