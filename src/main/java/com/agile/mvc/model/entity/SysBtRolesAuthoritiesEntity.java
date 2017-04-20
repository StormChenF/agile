package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_bt_roles_authorities",  catalog = "agile_db")
public class SysBtRolesAuthoritiesEntity implements Serializable {

    //唯一标识
    private Integer sysBtRolesAuthoritiesId;
    //权限唯一标识
    private Integer authorityId;
    //角色唯一标识
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

        return Objects.equals(sysBtRolesAuthoritiesId, that.sysBtRolesAuthoritiesId) &&Objects.equals(authorityId, that.authorityId) &&Objects.equals(roleId, that.roleId) ;
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
