package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/3/29.
 */
@Entity
@Table(name = "log_column", schema = "agile_db", catalog = "")
public class LogColumnEntity {
    private int logColumnId;
    private int logTableId;
    private String columnName;
    private String columnType;
    private String newValue;
    private String oldValue;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "log_column_id")
    public int getLogColumnId() {
        return logColumnId;
    }

    public void setLogColumnId(int logColumnId) {
        this.logColumnId = logColumnId;
    }

    @Basic
    @Column(name = "log_table_id")
    public int getLogTableId() {
        return logTableId;
    }

    public void setLogTableId(int logTableId) {
        this.logTableId = logTableId;
    }

    @Basic
    @Column(name = "column_name")
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Basic
    @Column(name = "column_type")
    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    @Basic
    @Column(name = "new_value")
    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Basic
    @Column(name = "old_value")
    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogColumnEntity that = (LogColumnEntity) o;

        if (logColumnId != that.logColumnId) return false;
        if (logTableId != that.logTableId) return false;
        if (columnName != null ? !columnName.equals(that.columnName) : that.columnName != null) return false;
        if (columnType != null ? !columnType.equals(that.columnType) : that.columnType != null) return false;
        if (newValue != null ? !newValue.equals(that.newValue) : that.newValue != null) return false;
        if (oldValue != null ? !oldValue.equals(that.oldValue) : that.oldValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logColumnId;
        result = 31 * result + logTableId;
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + (columnType != null ? columnType.hashCode() : 0);
        result = 31 * result + (newValue != null ? newValue.hashCode() : 0);
        result = 31 * result + (oldValue != null ? oldValue.hashCode() : 0);
        return result;
    }
}
