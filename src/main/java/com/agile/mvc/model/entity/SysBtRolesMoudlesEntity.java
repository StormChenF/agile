package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_bt_roles_moudles",  catalog = "agile_db")
public class SysBtRolesMoudlesEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private int sysBtRolesMoudlesId;
    //模块唯一标识
    private int moduleId;
    //角色唯一标识
    private int roleId;

    //无参构造器
    public SysBtRolesMoudlesEntity(){}

    //有参构造器
    public SysBtRolesMoudlesEntity(int sysBtRolesMoudlesId, int moduleId, int roleId ){
        this.sysBtRolesMoudlesId = sysBtRolesMoudlesId;
        this.moduleId = moduleId;
        this.roleId = roleId;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_BT_ROLES_MOUDLES_ID" )
    public int getSysBtRolesMoudlesId() {
        return sysBtRolesMoudlesId;
    }

    public void setsysBtRolesMoudlesId(int sysBtRolesMoudlesId) {
        this.sysBtRolesMoudlesId = sysBtRolesMoudlesId;
    }

    @Basic
    @Column(name = "MODULE_ID" )
    public int getModuleId() {
        return moduleId;
    }

    public void setmoduleId(int moduleId) {
        this.moduleId = moduleId;
    }

    @Basic
    @Column(name = "ROLE_ID" )
    public int getRoleId() {
        return roleId;
    }

    public void setroleId(int roleId) {
        this.roleId = roleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysBtRolesMoudlesEntity that = (SysBtRolesMoudlesEntity) o;

        return Objects.equals(sysBtRolesMoudlesId, that.sysBtRolesMoudlesId) &&Objects.equals(moduleId, that.moduleId) &&Objects.equals(roleId, that.roleId) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + sysBtRolesMoudlesId;
        result = 31 * result + moduleId;
        result = 31 * result + roleId;
        return result;
    }

    @Override
    public String toString() {
        return "SysBtRolesMoudlesEntity{" +
        "sysBtRolesMoudlesId=" + sysBtRolesMoudlesId +
        ",moduleId=" + moduleId +
        ",roleId=" + roleId +
        '}';
    }
}
