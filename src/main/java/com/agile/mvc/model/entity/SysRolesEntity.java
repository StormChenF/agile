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
    private Integer sysRolesId;
    //角色名称
    private String roleName;
    //角色说明
    private String roleDesc;
    //是否可用
    private Boolean enable;
    //是否系统权限
    private Boolean issys;
    //模块
    private String moduleId;

    //无参构造器
    public SysRolesEntity(){}

    //有参构造器
    public SysRolesEntity(Integer sysRolesId,String roleName,String roleDesc,Boolean enable,Boolean issys,String moduleId){
        this.sysRolesId = sysRolesId;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.enable = enable;
        this.issys = issys;
        this.moduleId = moduleId;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "sys_roles_id" )
    public Integer getSysRolesId() {
        return sysRolesId;
    }

    public void setSysRolesId(int sysRolesId) {
        this.sysRolesId = sysRolesId;
    }

    @Basic
    @Column(name = "role_name" , nullable = false )
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_desc" , nullable = false )
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Basic
    @Column(name = "enable" , nullable = false )
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "issys" , nullable = false )
    public Boolean getIssys() {
        return issys;
    }

    public void setIssys(Boolean issys) {
        this.issys = issys;
    }

    @Basic
    @Column(name = "module_id" , nullable = false )
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysRolesEntity)) return false;
        SysRolesEntity that = (SysRolesEntity) object;
        return Objects.equals(getSysRolesId(), that.getSysRolesId()) &&
            Objects.equals(getRoleName(), that.getRoleName()) &&
            Objects.equals(getRoleDesc(), that.getRoleDesc()) &&
            Objects.equals(getEnable(), that.getEnable()) &&
            Objects.equals(getIssys(), that.getIssys()) &&
            Objects.equals(getModuleId(), that.getModuleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysRolesId(), getRoleName(), getRoleDesc(), getEnable(), getIssys(), getModuleId());
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
