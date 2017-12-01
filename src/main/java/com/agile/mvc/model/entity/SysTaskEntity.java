package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Date;

/**
 * Created by 佟盟
 */
@Entity
@Table(name = "sys_task",  catalog = "agile_db")
public class SysTaskEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //主键
    private Integer id;
    //任务名
    private String name;
    //状态
    private boolean state;
    //定时表达式
    private String cron;
    //分布式同步锁名
    private String lockName;
    //操作时间
    private Date createTime;

    //无参构造器
    public SysTaskEntity(){}

    //有参构造器
    public SysTaskEntity(Integer id,String name,boolean state,String cron,String lockName,Date createTime){
        this.id = id;
        this.name = name;
        this.state = state;
        this.cron = cron;
        this.lockName = lockName;
        this.createTime = createTime;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id" )
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name" )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "state" )
    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Basic
    @Column(name = "cron" )
    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    @Basic
    @Column(name = "lock_name" )
    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    @Basic
    @Column(name = "create_time" )
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysTaskEntity that = (SysTaskEntity) o;

        return 
            Objects.equals(id, that.id)  && 
            (name != null ? name.equals(that.name) : that.name == null)  && 
            state == that.state  && 
            (cron != null ? cron.equals(that.cron) : that.cron == null)  && 
            (lockName != null ? lockName.equals(that.lockName) : that.lockName == null)  && 
            (getCreateTime() != null ? getCreateTime().equals(that.getCreateTime()) : that.getCreateTime() == null) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (state ? 1 : 0);
        result = 31 * result + (cron != null ? cron.hashCode() : 0);
        result = 31 * result + (lockName != null ? lockName.hashCode() : 0);
        result = 31 * result + (getCreateTime() != null ? getCreateTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SysTaskEntity{" +
        "id=" + id +
        ",name='" + name + '\'' +
        ",state=" + state +
        ",cron='" + cron + '\'' +
        ",lockName='" + lockName + '\'' +
        ",createTime=" + createTime +
        '}';
    }
}
