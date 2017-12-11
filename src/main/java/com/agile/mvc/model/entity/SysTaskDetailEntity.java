package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by 佟盟
 */
@Entity
@Table(name = "sys_task_detail",  catalog = "agile_db")
public class SysTaskDetailEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //主键
    private Integer sysTaskDetailId;
    //定时任务标志
    private int sysTaskId;
    //目标方法包路径
    private String targetPackage;
    //目标方法类名
    private String targetClass;
    //目标方法名
    private String targetMethod;
    //优先级
    private String order;

    //无参构造器
    public SysTaskDetailEntity(){}

    //有参构造器
    public SysTaskDetailEntity(Integer sysTaskDetailId,int sysTaskId,String targetPackage,String targetClass,String targetMethod,String order){
        this.sysTaskDetailId = sysTaskDetailId;
        this.sysTaskId = sysTaskId;
        this.targetPackage = targetPackage;
        this.targetClass = targetClass;
        this.targetMethod = targetMethod;
        this.order = order;
    }

    @Id
    @Column(name = "sys_task_detail_id" )
    public Integer getSysTaskDetailId() {
        return sysTaskDetailId;
    }

    public void setSysTaskDetailId(int sysTaskDetailId) {
        this.sysTaskDetailId = sysTaskDetailId;
    }

    @Basic
    @Column(name = "sys_task_id" , nullable = false )
    public int getSysTaskId() {
        return sysTaskId;
    }

    public void setSysTaskId(int sysTaskId) {
        this.sysTaskId = sysTaskId;
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
    @Column(name = "order" , nullable = false )
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysTaskDetailEntity that = (SysTaskDetailEntity) o;

        return 
            Objects.equals(sysTaskDetailId, that.sysTaskDetailId)  && 
            Objects.equals(sysTaskId, that.sysTaskId)  && 
            (targetPackage != null ? targetPackage.equals(that.targetPackage) : that.targetPackage == null)  && 
            (targetClass != null ? targetClass.equals(that.targetClass) : that.targetClass == null)  && 
            (targetMethod != null ? targetMethod.equals(that.targetMethod) : that.targetMethod == null)  && 
            (order != null ? order.equals(that.order) : that.order == null) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (getSysTaskDetailId() != null ? getSysTaskDetailId().hashCode() : 0);
        result = 31 * result + sysTaskId;
        result = 31 * result + (targetPackage != null ? targetPackage.hashCode() : 0);
        result = 31 * result + (targetClass != null ? targetClass.hashCode() : 0);
        result = 31 * result + (targetMethod != null ? targetMethod.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SysTaskDetailEntity{" +
        "sysTaskDetailId=" + sysTaskDetailId +
        ",sysTaskId=" + sysTaskId +
        ",targetPackage='" + targetPackage + '\'' +
        ",targetClass='" + targetClass + '\'' +
        ",targetMethod='" + targetMethod + '\'' +
        ",order='" + order + '\'' +
        '}';
    }
}
