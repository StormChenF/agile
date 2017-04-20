package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_roles",  catalog = "agile_db")
public class SysRolesEntity implements Serializable {

    //角色唯一标识
    private Integer sysRolesId;
    //角色名称
    private String roleName;
    //角色说明
    private String roleDesc;
    //是否可用
    private Boolean eNABLE;
    //是否系统权限
    private Boolean iSSYS;
    //模块
    private String moduleId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_ROLES_ID")
    public Integer getSysRolesId() {
        return sysRolesId;
    }

    public void setsysRolesId(int sysRolesId) {
        this.sysRolesId = sysRolesId;
    }

    @Basic
    @Column(name = "ROLE_NAME")
    public String getRoleName() {
        return roleName;
    }

    public void setroleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "ROLE_DESC")
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setroleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Basic
    @Column(name = "ENABLE")
    public Boolean getENABLE() {
        return eNABLE;
    }

    public void seteNABLE(Boolean eNABLE) {
        this.eNABLE = eNABLE;
    }

    @Basic
    @Column(name = "ISSYS")
    public Boolean getISSYS() {
        return iSSYS;
    }

    public void setiSSYS(Boolean iSSYS) {
        this.iSSYS = iSSYS;
    }

    @Basic
    @Column(name = "MODULE_ID")
    public String getModuleId() {
        return moduleId;
    }

    public void setmoduleId(String moduleId) {
        this.moduleId = moduleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRolesEntity that = (SysRolesEntity) o;

        if (sysRolesId != that.sysRolesId) return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;
        if (roleDesc != null ? !roleDesc.equals(that.roleDesc) : that.roleDesc != null) return false;
        if (eNABLE != that.eNABLE) return false;
        if (iSSYS != that.iSSYS) return false;
        if (moduleId != null ? !moduleId.equals(that.moduleId) : that.moduleId != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + sysRolesId;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (roleDesc != null ? roleDesc.hashCode() : 0);
        result = 31 * result + (eNABLE ? 1 : 0);
        result = 31 * result + (iSSYS ? 1 : 0);
        result = 31 * result + (moduleId != null ? moduleId.hashCode() : 0);
        return result;
    }
}
