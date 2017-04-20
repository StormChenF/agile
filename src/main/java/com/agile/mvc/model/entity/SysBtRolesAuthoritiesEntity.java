package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_bt_roles_authorities",  catalog = "agile_db")
public class SysBtRolesAuthoritiesEntity {

    private Integer sysBtRolesAuthoritiesId;
    private Integer authorityId;
    private Integer roleId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_BT_ROLES_AUTHORITIES_ID")
    public Integer getSysBtRolesAuthoritiesId() {
        return sysBtRolesAuthoritiesId;
    }

    public void setsysBtRolesAuthoritiesId(int sysBtRolesAuthoritiesId) {
        this.sysBtRolesAuthoritiesId = sysBtRolesAuthoritiesId;
    }

    @Basic
    @Column(name = "AUTHORITY_ID")
    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setauthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Basic
    @Column(name = "ROLE_ID")
    public Integer getRoleId() {
        return roleId;
    }

    public void setroleId(int roleId) {
        this.roleId = roleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysBtRolesAuthoritiesEntity that = (SysBtRolesAuthoritiesEntity) o;

        if (sysBtRolesAuthoritiesId != that.sysBtRolesAuthoritiesId) return false;
        if (authorityId != that.authorityId) return false;
        if (roleId != that.roleId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + sysBtRolesAuthoritiesId;
        result = 31 * result + authorityId;
        result = 31 * result + roleId;
        return result;
    }
}
