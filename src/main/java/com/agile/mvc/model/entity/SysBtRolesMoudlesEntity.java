package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_bt_roles_moudles",  catalog = "agile_db")
public class SysBtRolesMoudlesEntity implements Serializable {

    //唯一标识
    private Integer sysBtRolesMoudlesId;
    //模块唯一标识
    private Integer moduleId;
    //角色唯一标识
    private Integer roleId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_BT_ROLES_MOUDLES_ID")
    public Integer getSysBtRolesMoudlesId() {
        return sysBtRolesMoudlesId;
    }

    public void setsysBtRolesMoudlesId(int sysBtRolesMoudlesId) {
        this.sysBtRolesMoudlesId = sysBtRolesMoudlesId;
    }

    @Basic
    @Column(name = "MODULE_ID")
    public Integer getModuleId() {
        return moduleId;
    }

    public void setmoduleId(int moduleId) {
        this.moduleId = moduleId;
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

        SysBtRolesMoudlesEntity that = (SysBtRolesMoudlesEntity) o;

        if (sysBtRolesMoudlesId != that.sysBtRolesMoudlesId) return false;
        if (moduleId != that.moduleId) return false;
        if (roleId != that.roleId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + sysBtRolesMoudlesId;
        result = 31 * result + moduleId;
        result = 31 * result + roleId;
        return result;
    }
}
