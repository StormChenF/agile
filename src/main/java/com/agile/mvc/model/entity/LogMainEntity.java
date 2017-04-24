package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.sql.Date;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "log_main",  catalog = "agile_db")
public class LogMainEntity implements Serializable {
    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private Integer logMainId;
    //业务编码
    private String businessCode;
    //业务对象类型
    private String targetType;
    //业务对象标识
    private String targetCode;
    //操作人
    private Integer userId;
    //操作时间
    private Date createTime;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "log_main_id" , nullable  = true)
    public Integer getLogMainId() {
        return logMainId;
    }

    public void setlogMainId(int logMainId) {
        this.logMainId = logMainId;
    }

    @Basic
    @Column(name = "business_code" , nullable  = true)
    public String getBusinessCode() {
        return businessCode;
    }

    public void setbusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    @Basic
    @Column(name = "target_type" , nullable  = true)
    public String getTargetType() {
        return targetType;
    }

    public void settargetType(String targetType) {
        this.targetType = targetType;
    }

    @Basic
    @Column(name = "target_code" , nullable  = true)
    public String getTargetCode() {
        return targetCode;
    }

    public void settargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    @Basic
    @Column(name = "user_id" , nullable  = true)
    public Integer getUserId() {
        return userId;
    }

    public void setuserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "create_time" , nullable  = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setcreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogMainEntity that = (LogMainEntity) o;

        return Objects.equals(logMainId, that.logMainId) &&(businessCode != null ? businessCode.equals(that.businessCode) : that.businessCode == null) &&(targetType != null ? targetType.equals(that.targetType) : that.targetType == null) &&(targetCode != null ? targetCode.equals(that.targetCode) : that.targetCode == null) &&Objects.equals(userId, that.userId) &&createTime == that.createTime ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + logMainId;
        result = 31 * result + (businessCode != null ? businessCode.hashCode() : 0);
        result = 31 * result + (targetType != null ? targetType.hashCode() : 0);
        result = 31 * result + (targetCode != null ? targetCode.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
