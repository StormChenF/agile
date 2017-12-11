package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Date;
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
    private Integer sysTaskId;
    //定时任务名
    private String name;
    //状态
    private Boolean state;
    //定时表达式
    private String cron;
    //是否同步
    private Boolean sync;
    //更新时间
    private Date updateTime;
    //创建时间
    private Date createTime;

    //无参构造器
    public SysTaskEntity(){}

    //有参构造器
    public SysTaskEntity(Integer sysTaskId,String name,Boolean state,String cron,Boolean sync,Date updateTime,Date createTime){
        this.sysTaskId = sysTaskId;
        this.name = name;
        this.state = state;
        this.cron = cron;
        this.sync = sync;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "sys_task_id" )
    public Integer getSysTaskId() {
        return sysTaskId;
    }

    public void setSysTaskId(int sysTaskId) {
        this.sysTaskId = sysTaskId;
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
    @Column(name = "state" , nullable = false )
    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Basic
    @Column(name = "cron" , nullable = false )
    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    @Basic
    @Column(name = "sync" , nullable = false )
    public Boolean getSync() {
        return sync;
    }

    public void setSync(Boolean sync) {
        this.sync = sync;
    }

    @Basic
    @Column(name = "update_time" , nullable = false )
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "create_time" , nullable = false )
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
            Objects.equals(sysTaskId, that.sysTaskId)  && 
            (name != null ? name.equals(that.name) : that.name == null)  && 
            state == that.state  && 
            (cron != null ? cron.equals(that.cron) : that.cron == null)  && 
            sync == that.sync  && 
            (getUpdateTime() != null ? getUpdateTime().equals(that.getUpdateTime()) : that.getUpdateTime() == null)  && 
            (getCreateTime() != null ? getCreateTime().equals(that.getCreateTime()) : that.getCreateTime() == null) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (getSysTaskId() != null ? getSysTaskId().hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (getState() != null && state ? 1 : 0);
        result = 31 * result + (cron != null ? cron.hashCode() : 0);
        result = 31 * result + (getSync() != null && sync ? 1 : 0);
        result = 31 * result + (getUpdateTime() != null ? getUpdateTime().hashCode() : 0);
        result = 31 * result + (getCreateTime() != null ? getCreateTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SysTaskEntity{" +
        "sysTaskId=" + sysTaskId +
        ",name='" + name + '\'' +
        ",state=" + state +
        ",cron='" + cron + '\'' +
        ",sync=" + sync +
        ",updateTime=" + updateTime +
        ",createTime=" + createTime +
        '}';
    }
}
