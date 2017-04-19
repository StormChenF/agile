package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/4/17.
 */
@Entity
@Table(name = "sys_roles", schema = "agile_db", catalog = "")
public class SysRolesEntity {
    private String roleName;
    private String roleDesc;
    private Boolean enable;
    private Boolean issys;
    private String moduleId;
    private int sysRolesId;

    @Basic
    @Column(name = "ROLE_NAME")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "ROLE_DESC")
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Basic
    @Column(name = "ENABLE")
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "ISSYS")
    public Boolean getIssys() {
        return issys;
    }

    public void setIssys(Boolean issys) {
        this.issys = issys;
    }

    @Basic
    @Column(name = "MODULE_ID")
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Id
    @Column(name = "SYS_ROLES_ID")
    public int getSysRolesId() {
        return sysRolesId;
    }

    public void setSysRolesId(int sysRolesId) {
        this.sysRolesId = sysRolesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRolesEntity that = (SysRolesEntity) o;

        if (sysRolesId != that.sysRolesId) return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;
        if (roleDesc != null ? !roleDesc.equals(that.roleDesc) : that.roleDesc != null) return false;
        if (enable != null ? !enable.equals(that.enable) : that.enable != null) return false;
        if (issys != null ? !issys.equals(that.issys) : that.issys != null) return false;
        if (moduleId != null ? !moduleId.equals(that.moduleId) : that.moduleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleName != null ? roleName.hashCode() : 0;
        result = 31 * result + (roleDesc != null ? roleDesc.hashCode() : 0);
        result = 31 * result + (enable != null ? enable.hashCode() : 0);
        result = 31 * result + (issys != null ? issys.hashCode() : 0);
        result = 31 * result + (moduleId != null ? moduleId.hashCode() : 0);
        result = 31 * result + sysRolesId;
        return result;
    }
}
