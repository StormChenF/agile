package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mydeathtrial on 2017/3/21.
 */
@Entity
@Table(name = "t_sys_log", schema = "agile_db")
public class TSysLogEntity {
    private int id;
    private String businessCode;
    private String objectType;
    private String objectCode;
    private int staffId;
    private Timestamp createTime;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "business_code", nullable = false)
    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessId) {
        this.businessCode = businessId;
    }

    @Basic
    @Column(name = "object_type", nullable = false, length = 4)
    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Basic
    @Column(name = "object_code", nullable = false, length = 100)
    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    @Basic
    @Column(name = "staff_id", nullable = false)
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
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

        TSysLogEntity that = (TSysLogEntity) o;

        if (id != that.id) return false;
        if (businessCode != that.businessCode) return false;
        if (staffId != that.staffId) return false;
        if (objectType != null ? !objectType.equals(that.objectType) : that.objectType != null) return false;
        if (objectCode != null ? !objectCode.equals(that.objectCode) : that.objectCode != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (businessCode != null ? businessCode.hashCode() : 0);
        result = 31 * result + (objectType != null ? objectType.hashCode() : 0);
        result = 31 * result + (objectCode != null ? objectCode.hashCode() : 0);
        result = 31 * result + staffId;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
