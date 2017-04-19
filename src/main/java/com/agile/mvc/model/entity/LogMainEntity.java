package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mydeathtrial on 2017/4/17.
 */
@Entity
@Table(name = "log_main", schema = "agile_db", catalog = "")
public class LogMainEntity {
    private int logMainId;
    private String businessCode;
    private String targetType;
    private String targetCode;
    private int userId;
    private Timestamp createTime;

    @Id
    @Column(name = "log_main_id")
    public int getLogMainId() {
        return logMainId;
    }

    public void setLogMainId(int logMainId) {
        this.logMainId = logMainId;
    }

    @Basic
    @Column(name = "business_code")
    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    @Basic
    @Column(name = "target_type")
    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    @Basic
    @Column(name = "target_code")
    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogMainEntity that = (LogMainEntity) o;

        if (logMainId != that.logMainId) return false;
        if (userId != that.userId) return false;
        if (businessCode != null ? !businessCode.equals(that.businessCode) : that.businessCode != null) return false;
        if (targetType != null ? !targetType.equals(that.targetType) : that.targetType != null) return false;
        if (targetCode != null ? !targetCode.equals(that.targetCode) : that.targetCode != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logMainId;
        result = 31 * result + (businessCode != null ? businessCode.hashCode() : 0);
        result = 31 * result + (targetType != null ? targetType.hashCode() : 0);
        result = 31 * result + (targetCode != null ? targetCode.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
