package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/3/16.
 */
@Entity
@Table(name = "sys_bt_roles_authorities", schema = "agile_db", catalog = "")
public class SysBtRolesAuthoritiesEntity {
    private int id;
    private int authorityId;
    private int roleId;

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
    @Column(name = "AUTHORITY_ID", nullable = false)
    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Basic
    @Column(name = "ROLE_ID", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysBtRolesAuthoritiesEntity that = (SysBtRolesAuthoritiesEntity) o;

        if (id != that.id) return false;
        if (authorityId != that.authorityId) return false;
        if (roleId != that.roleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + authorityId;
        result = 31 * result + roleId;
        return result;
    }
}
