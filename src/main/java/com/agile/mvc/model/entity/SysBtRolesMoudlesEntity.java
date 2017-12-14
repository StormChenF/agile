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
    private Integer sysBtRolesMoudlesId;
    //模块唯一标识
    private int moduleId;
    //角色唯一标识
    private int roleId;

    //无参构造器
    public SysBtRolesMoudlesEntity(){}

    //有参构造器
    public SysBtRolesMoudlesEntity(Integer sysBtRolesMoudlesId,int moduleId,int roleId){
        this.sysBtRolesMoudlesId = sysBtRolesMoudlesId;
        this.moduleId = moduleId;
        this.roleId = roleId;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "sys_bt_roles_moudles_id" , nullable = false )
    public Integer getSysBtRolesMoudlesId() {
        return sysBtRolesMoudlesId;
    }

    public void setSysBtRolesMoudlesId(int sysBtRolesMoudlesId) {
        this.sysBtRolesMoudlesId = sysBtRolesMoudlesId;
    }

    @Basic
    @Column(name = "module_id" , nullable = false )
    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
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
        if (!(object instanceof SysBtRolesMoudlesEntity)) return false;
        SysBtRolesMoudlesEntity that = (SysBtRolesMoudlesEntity) object;
        return Objects.equals(getSysBtRolesMoudlesId(), that.getSysBtRolesMoudlesId()) &&
            Objects.equals(getModuleId(), that.getModuleId()) &&
            Objects.equals(getRoleId(), that.getRoleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysBtRolesMoudlesId(), getModuleId(), getRoleId());
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
