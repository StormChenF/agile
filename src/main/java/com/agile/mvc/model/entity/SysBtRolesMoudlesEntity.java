package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/3/16.
 */
@Entity
@Table(name = "sys_bt_roles_moudles", schema = "agile_db", catalog = "")
public class SysBtRolesMoudlesEntity {
    private int id;
    private int moduleId;
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
    @Column(name = "MODULE_ID", nullable = false)
    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
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

        SysBtRolesMoudlesEntity that = (SysBtRolesMoudlesEntity) o;

        if (id != that.id) return false;
        if (moduleId != that.moduleId) return false;
        if (roleId != that.roleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + moduleId;
        result = 31 * result + roleId;
        return result;
    }
}
