package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by 佟盟
 */
@Entity
@Table(name = "sys_bt_task_target",  catalog = "agile_db")
public class SysBtTaskTargetEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //主键
    private Integer sysBtTaskTargetId;
    //定时任务标志
    private int sysTaskId;
    //目标方法主键
    private int sysTaskTarget;
    //优先级
    private String order;
    //
    private int sysTaskDetailId;

    //无参构造器
    public SysBtTaskTargetEntity(){}

    //有参构造器
    public SysBtTaskTargetEntity(Integer sysBtTaskTargetId,int sysTaskId,int sysTaskTarget,String order,int sysTaskDetailId){
        this.sysBtTaskTargetId = sysBtTaskTargetId;
        this.sysTaskId = sysTaskId;
        this.sysTaskTarget = sysTaskTarget;
        this.order = order;
        this.sysTaskDetailId = sysTaskDetailId;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "sys_bt_task_target_id" , nullable = false )
    public Integer getSysBtTaskTargetId() {
        return sysBtTaskTargetId;
    }

    public void setSysBtTaskTargetId(int sysBtTaskTargetId) {
        this.sysBtTaskTargetId = sysBtTaskTargetId;
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
    @Column(name = "sys_task_target" , nullable = false )
    public int getSysTaskTarget() {
        return sysTaskTarget;
    }

    public void setSysTaskTarget(int sysTaskTarget) {
        this.sysTaskTarget = sysTaskTarget;
    }

    @Basic
    @Column(name = "order" , nullable = false )
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Basic
    @Column(name = "sys_task_detail_id" , nullable = false )
    public int getSysTaskDetailId() {
        return sysTaskDetailId;
    }

    public void setSysTaskDetailId(int sysTaskDetailId) {
        this.sysTaskDetailId = sysTaskDetailId;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysBtTaskTargetEntity)) return false;
        SysBtTaskTargetEntity that = (SysBtTaskTargetEntity) object;
        return Objects.equals(getSysBtTaskTargetId(), that.getSysBtTaskTargetId()) &&
            Objects.equals(getSysTaskId(), that.getSysTaskId()) &&
            Objects.equals(getSysTaskTarget(), that.getSysTaskTarget()) &&
            Objects.equals(getOrder(), that.getOrder()) &&
            Objects.equals(getSysTaskDetailId(), that.getSysTaskDetailId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysBtTaskTargetId(), getSysTaskId(), getSysTaskTarget(), getOrder(), getSysTaskDetailId());
    }

    @Override
    public String toString() {
        return "SysBtTaskTargetEntity{" +
        "sysBtTaskTargetId=" + sysBtTaskTargetId +
        ",sysTaskId=" + sysTaskId +
        ",sysTaskTarget=" + sysTaskTarget +
        ",order='" + order + '\'' +
        ",sysTaskDetailId=" + sysTaskDetailId +
        '}';
    }
}
