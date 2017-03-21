package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/3/21.
 */
@Entity
@Table(name = "t_sys_log_detail", schema = "agile_db", catalog = "")
public class TSysLogDetailEntity {
    private int id;
    private int logId;
    private String newValue;
    private String oldValue;
    private String tableName;
    private String columName;
    private String columType;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "log_id", nullable = false)
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "new_value", nullable = false, length = 100)
    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Basic
    @Column(name = "old_value", nullable = false, length = 100)
    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    @Basic
    @Column(name = "table_name", nullable = false, length = 100)
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "colum_name", nullable = true, length = 100)
    public String getColumName() {
        return columName;
    }

    public void setColumName(String columName) {
        this.columName = columName;
    }

    @Basic
    @Column(name = "colum_type", nullable = true, length = 20)
    public String getColumType() {
        return columType;
    }

    public void setColumType(String columType) {
        this.columType = columType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TSysLogDetailEntity that = (TSysLogDetailEntity) o;

        if (id != that.id) return false;
        if (logId != that.logId) return false;
        if (newValue != null ? !newValue.equals(that.newValue) : that.newValue != null) return false;
        if (oldValue != null ? !oldValue.equals(that.oldValue) : that.oldValue != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (columName != null ? !columName.equals(that.columName) : that.columName != null) return false;
        if (columType != null ? !columType.equals(that.columType) : that.columType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + logId;
        result = 31 * result + (newValue != null ? newValue.hashCode() : 0);
        result = 31 * result + (oldValue != null ? oldValue.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (columName != null ? columName.hashCode() : 0);
        result = 31 * result + (columType != null ? columType.hashCode() : 0);
        return result;
    }
}
