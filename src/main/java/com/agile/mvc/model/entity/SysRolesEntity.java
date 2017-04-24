package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_roles",  catalog = "agile_db")
public class SysRolesEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //角色唯一标识
    private int sysRolesId;
    //角色名称
    private String roleName;
    //角色说明
    private String roleDesc;
    //是否可用
    private boolean enable;
    //是否系统权限
    private boolean issys;
    //模块
    private String moduleId;

    //无参构造器
    public SysRolesEntity(){}

    //有参构造器
    public SysRolesEntity(int sysRolesId,String roleName,String roleDesc,boolean enable,boolean issys,String moduleId){
        this.sysRolesId = sysRolesId;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.enable = enable;
        this.issys = issys;
        this.moduleId = moduleId;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_ROLES_ID" )
    public int getSysRolesId() {
        return sysRolesId;
    }

    public void setsysRolesId(int sysRolesId) {
        this.sysRolesId = sysRolesId;
    }

    @Basic
    @Column(name = "ROLE_NAME" , nullable = false )
    public String getRoleName() {
        return roleName;
    }

    public void setroleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "ROLE_DESC" , nullable = false )
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setroleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Basic
    @Column(name = "ENABLE" , nullable = false )
    public boolean getENABLE() {
        return enable;
    }

    public void seteNABLE(boolean enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "ISSYS" , nullable = false )
    public boolean getISSYS() {
        return issys;
    }

    public void setiSSYS(boolean issys) {
        this.issys = issys;
    }

    @Basic
    @Column(name = "MODULE_ID" , nullable = false )
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

        return 
            Objects.equals(sysRolesId, that.sysRolesId)  && 
            (roleName != null ? roleName.equals(that.roleName) : that.roleName == null)  && 
            (roleDesc != null ? roleDesc.equals(that.roleDesc) : that.roleDesc == null)  && 
            enable == that.enable  && 
            issys == that.issys  && 
            (moduleId != null ? moduleId.equals(that.moduleId) : that.moduleId == null) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + sysRolesId;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (roleDesc != null ? roleDesc.hashCode() : 0);
        result = 31 * result + (enable ? 1 : 0);
        result = 31 * result + (issys ? 1 : 0);
        result = 31 * result + (moduleId != null ? moduleId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SysRolesEntity{" +
        "sysRolesId=" + sysRolesId +
        ",roleName='" + roleName + '\'' +
        ",roleDesc='" + roleDesc + '\'' +
        ",enable=" + enable +
        ",issys=" + issys +
        ",moduleId='" + moduleId + '\'' +
        '}';
    }
}
