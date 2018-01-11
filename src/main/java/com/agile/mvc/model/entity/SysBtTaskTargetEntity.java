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
    private int sysTaskTargetId;
    //优先级
    private String order;
    //
    private int sysTaskDetailId;
    //
    private int sysTaskTarget;

    //无参构造器
    public SysBtTaskTargetEntity(){}

    //有参构造器
    public SysBtTaskTargetEntity(Integer sysBtTaskTargetId,int sysTaskId,int sysTaskTargetId,String order,int sysTaskDetailId,int sysTaskTarget){
        this.sysBtTaskTargetId = sysBtTaskTargetId;
        this.sysTaskId = sysTaskId;
        this.sysTaskTargetId = sysTaskTargetId;
        this.order = order;
        this.sysTaskDetailId = sysTaskDetailId;
        this.sysTaskTarget = sysTaskTarget;
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
    @Column(name = "sys_task_target_id" , nullable = false )
    public int getSysTaskTargetId() {
        return sysTaskTargetId;
    }

    public void setSysTaskTargetId(int sysTaskTargetId) {
        this.sysTaskTargetId = sysTaskTargetId;
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

    @Basic
    @Column(name = "sys_task_target" , nullable = false )
    public int getSysTaskTarget() {
        return sysTaskTarget;
    }

    public void setSysTaskTarget(int sysTaskTarget) {
        this.sysTaskTarget = sysTaskTarget;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysBtTaskTargetEntity)) return false;
        SysBtTaskTargetEntity that = (SysBtTaskTargetEntity) object;
        return Objects.equals(getSysBtTaskTargetId(), that.getSysBtTaskTargetId()) &&
            Objects.equals(getSysTaskId(), that.getSysTaskId()) &&
            Objects.equals(getSysTaskTargetId(), that.getSysTaskTargetId()) &&
            Objects.equals(getOrder(), that.getOrder()) &&
            Objects.equals(getSysTaskDetailId(), that.getSysTaskDetailId()) &&
            Objects.equals(getSysTaskTarget(), that.getSysTaskTarget());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysBtTaskTargetId(), getSysTaskId(), getSysTaskTargetId(), getOrder(), getSysTaskDetailId(), getSysTaskTarget());
    }

    @Override
    public String toString() {
        return "SysBtTaskTargetEntity{" +
        "sysBtTaskTargetId=" + sysBtTaskTargetId +
        ",sysTaskId=" + sysTaskId +
        ",sysTaskTargetId=" + sysTaskTargetId +
        ",order='" + order + '\'' +
        ",sysTaskDetailId=" + sysTaskDetailId +
        ",sysTaskTarget=" + sysTaskTarget +
        '}';
    }

    private SysBtTaskTargetEntity(Builder builder){
        this.sysBtTaskTargetId = builder.sysBtTaskTargetId;
        this.sysTaskId = builder.sysTaskId;
        this.sysTaskTargetId = builder.sysTaskTargetId;
        this.order = builder.order;
        this.sysTaskDetailId = builder.sysTaskDetailId;
        this.sysTaskTarget = builder.sysTaskTarget;
    }

    public static class Builder{
        private Integer sysBtTaskTargetId;
        private int sysTaskId;
        private int sysTaskTargetId;
        private String order;
        private int sysTaskDetailId;
        private int sysTaskTarget;

        public Builder setSysBtTaskTargetId(int sysBtTaskTargetId) {
            this.sysBtTaskTargetId = sysBtTaskTargetId;
            return this;
        }
        public Builder setSysTaskId(int sysTaskId) {
            this.sysTaskId = sysTaskId;
            return this;
        }
        public Builder setSysTaskTargetId(int sysTaskTargetId) {
            this.sysTaskTargetId = sysTaskTargetId;
            return this;
        }
        public Builder setOrder(String order) {
            this.order = order;
            return this;
        }
        public Builder setSysTaskDetailId(int sysTaskDetailId) {
            this.sysTaskDetailId = sysTaskDetailId;
            return this;
        }
        public Builder setSysTaskTarget(int sysTaskTarget) {
            this.sysTaskTarget = sysTaskTarget;
            return this;
        }
        public SysBtTaskTargetEntity build(){
            return new SysBtTaskTargetEntity(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
