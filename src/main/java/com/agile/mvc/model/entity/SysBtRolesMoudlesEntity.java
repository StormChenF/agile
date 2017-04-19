package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/4/17.
 */
@Entity
@Table(name = "sys_bt_roles_moudles", schema = "agile_db", catalog = "")
public class SysBtRolesMoudlesEntity {
    private int moduleId;
    private int roleId;
    private int sysBtRolesMoudlesId;

    @Basic
    @Column(name = "MODULE_ID")
    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
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
    @Column(name = "SYS_BT_ROLES_MOUDLES_ID")
    public int getSysBtRolesMoudlesId() {
        return sysBtRolesMoudlesId;
    }

    public void setSysBtRolesMoudlesId(int sysBtRolesMoudlesId) {
        this.sysBtRolesMoudlesId = sysBtRolesMoudlesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysBtRolesMoudlesEntity that = (SysBtRolesMoudlesEntity) o;

        if (moduleId != that.moduleId) return false;
        if (roleId != that.roleId) return false;
        if (sysBtRolesMoudlesId != that.sysBtRolesMoudlesId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = moduleId;
        result = 31 * result + roleId;
        result = 31 * result + sysBtRolesMoudlesId;
        return result;
    }
}
