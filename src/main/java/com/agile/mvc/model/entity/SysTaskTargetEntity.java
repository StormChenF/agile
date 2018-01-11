package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by 佟盟
 */
@Entity
@Table(name = "sys_task_target",  catalog = "agile_db")
public class SysTaskTargetEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private Integer sysTaskTargetId;
    //方法含义名
    private String name;
    //包名
    private String targetPackage;
    //类名
    private String targetClass;
    //方法名
    private String targetMethod;
    //备注
    private String remarks;

    //无参构造器
    public SysTaskTargetEntity(){}

    //有参构造器
    public SysTaskTargetEntity(Integer sysTaskTargetId,String name,String targetPackage,String targetClass,String targetMethod,String remarks){
        this.sysTaskTargetId = sysTaskTargetId;
        this.name = name;
        this.targetPackage = targetPackage;
        this.targetClass = targetClass;
        this.targetMethod = targetMethod;
        this.remarks = remarks;
    }

    @Id
    @Column(name = "sys_task_target_id" , nullable = false )
    public Integer getSysTaskTargetId() {
        return sysTaskTargetId;
    }

    public void setSysTaskTargetId(int sysTaskTargetId) {
        this.sysTaskTargetId = sysTaskTargetId;
    }

    @Basic
    @Column(name = "name" , nullable = false )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "target_package" , nullable = false )
    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    @Basic
    @Column(name = "target_class" , nullable = false )
    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    @Basic
    @Column(name = "target_method" , nullable = false )
    public String getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }

    @Basic
    @Column(name = "remarks" )
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysTaskTargetEntity)) return false;
        SysTaskTargetEntity that = (SysTaskTargetEntity) object;
        return Objects.equals(getSysTaskTargetId(), that.getSysTaskTargetId()) &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getTargetPackage(), that.getTargetPackage()) &&
            Objects.equals(getTargetClass(), that.getTargetClass()) &&
            Objects.equals(getTargetMethod(), that.getTargetMethod()) &&
            Objects.equals(getRemarks(), that.getRemarks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysTaskTargetId(), getName(), getTargetPackage(), getTargetClass(), getTargetMethod(), getRemarks());
    }

    @Override
    public String toString() {
        return "SysTaskTargetEntity{" +
        "sysTaskTargetId=" + sysTaskTargetId +
        ",name='" + name + '\'' +
        ",targetPackage='" + targetPackage + '\'' +
        ",targetClass='" + targetClass + '\'' +
        ",targetMethod='" + targetMethod + '\'' +
        ",remarks='" + remarks + '\'' +
        '}';
    }

    private SysTaskTargetEntity(Builder builder){
        this.sysTaskTargetId = builder.sysTaskTargetId;
        this.name = builder.name;
        this.targetPackage = builder.targetPackage;
        this.targetClass = builder.targetClass;
        this.targetMethod = builder.targetMethod;
        this.remarks = builder.remarks;
    }

    public static class Builder{
        private Integer sysTaskTargetId;
        private String name;
        private String targetPackage;
        private String targetClass;
        private String targetMethod;
        private String remarks;

        public Builder setSysTaskTargetId(int sysTaskTargetId) {
            this.sysTaskTargetId = sysTaskTargetId;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setTargetPackage(String targetPackage) {
            this.targetPackage = targetPackage;
            return this;
        }
        public Builder setTargetClass(String targetClass) {
            this.targetClass = targetClass;
            return this;
        }
        public Builder setTargetMethod(String targetMethod) {
            this.targetMethod = targetMethod;
            return this;
        }
        public Builder setRemarks(String remarks) {
            this.remarks = remarks;
            return this;
        }
        public SysTaskTargetEntity build(){
            return new SysTaskTargetEntity(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
