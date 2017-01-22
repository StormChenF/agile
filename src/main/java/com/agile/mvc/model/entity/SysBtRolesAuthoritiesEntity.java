package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by tongmeng on 2017/1/17.
 */
@Entity
@Table(name = "sys_bt_roles_authorities", schema = "agile_db", catalog = "")
public class SysBtRolesAuthoritiesEntity {
    private Integer id;
    private Integer authorityId;
    private Integer roleId;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AUTHORITY_ID", nullable = false)
    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    @Basic
    @Column(name = "ROLE_ID", nullable = false)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysBtRolesAuthoritiesEntity that = (SysBtRolesAuthoritiesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (authorityId != null ? !authorityId.equals(that.authorityId) : that.authorityId != null) return false;
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (authorityId != null ? authorityId.hashCode() : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }
}
