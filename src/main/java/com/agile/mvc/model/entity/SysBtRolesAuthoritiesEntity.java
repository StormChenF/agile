package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/4/17.
 */
@Entity
@Table(name = "sys_bt_roles_authorities", schema = "agile_db", catalog = "")
public class SysBtRolesAuthoritiesEntity {
    private int authorityId;
    private int roleId;
    private int sysBtRolesAuthoritiesId;

    @Basic
    @Column(name = "AUTHORITY_ID")
    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Basic
    @Column(name = "ROLE_ID")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Id
    @Column(name = "SYS_BT_ROLES_AUTHORITIES_ID")
    public int getSysBtRolesAuthoritiesId() {
        return sysBtRolesAuthoritiesId;
    }

    public void setSysBtRolesAuthoritiesId(int sysBtRolesAuthoritiesId) {
        this.sysBtRolesAuthoritiesId = sysBtRolesAuthoritiesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysBtRolesAuthoritiesEntity that = (SysBtRolesAuthoritiesEntity) o;

        if (authorityId != that.authorityId) return false;
        if (roleId != that.roleId) return false;
        if (sysBtRolesAuthoritiesId != that.sysBtRolesAuthoritiesId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authorityId;
        result = 31 * result + roleId;
        result = 31 * result + sysBtRolesAuthoritiesId;
        return result;
    }
}
