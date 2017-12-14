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

    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private Integer sysBtRolesAuthoritiesId;
    //权限唯一标识
    private int authorityId;
    //角色唯一标识
    private int roleId;

    //无参构造器
    public SysBtRolesAuthoritiesEntity(){}

    //有参构造器
    public SysBtRolesAuthoritiesEntity(Integer sysBtRolesAuthoritiesId,int authorityId,int roleId){
        this.sysBtRolesAuthoritiesId = sysBtRolesAuthoritiesId;
        this.authorityId = authorityId;
        this.roleId = roleId;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "sys_bt_roles_authorities_id" , nullable = false )
    public Integer getSysBtRolesAuthoritiesId() {
        return sysBtRolesAuthoritiesId;
    }

    public void setSysBtRolesAuthoritiesId(int sysBtRolesAuthoritiesId) {
        this.sysBtRolesAuthoritiesId = sysBtRolesAuthoritiesId;
    }

    @Basic
    @Column(name = "authority_id" , nullable = false )
    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Basic
    @Column(name = "role_id" , nullable = false )
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysBtRolesAuthoritiesEntity)) return false;
        SysBtRolesAuthoritiesEntity that = (SysBtRolesAuthoritiesEntity) object;
        return Objects.equals(getSysBtRolesAuthoritiesId(), that.getSysBtRolesAuthoritiesId()) &&
            Objects.equals(getAuthorityId(), that.getAuthorityId()) &&
            Objects.equals(getRoleId(), that.getRoleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysBtRolesAuthoritiesId(), getAuthorityId(), getRoleId());
    }

    @Override
    public String toString() {
        return "SysBtRolesAuthoritiesEntity{" +
        "sysBtRolesAuthoritiesId=" + sysBtRolesAuthoritiesId +
        ",authorityId=" + authorityId +
        ",roleId=" + roleId +
        '}';
    }
}
