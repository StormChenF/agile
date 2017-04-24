package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "log_value",  catalog = "agile_db")
public class LogValueEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private int logValueId;
    //日志相关表标识
    private int logTableId;
    //字段
    private String columnName;
    //字段类型
    private String columnType;
    //新值
    private String newValue;
    //旧值
    private String oldValue;
    //字段含义
    private String columnInfo;

    //无参构造器
    public LogValueEntity(){}

    //有参构造器
    public LogValueEntity(int logValueId, int logTableId, String columnName, String columnType, String newValue, String oldValue, String columnInfo ){
        this.logValueId = logValueId;
        this.logTableId = logTableId;
        this.columnName = columnName;
        this.columnType = columnType;
        this.newValue = newValue;
        this.oldValue = oldValue;
        this.columnInfo = columnInfo;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "log_value_id" )
    public int getLogValueId() {
        return logValueId;
    }

    public void setlogValueId(int logValueId) {
        this.logValueId = logValueId;
    }

    @Basic
    @Column(name = "log_table_id" )
    public int getLogTableId() {
        return logTableId;
    }

    public void setlogTableId(int logTableId) {
        this.logTableId = logTableId;
    }

    @Basic
    @Column(name = "column_name" )
    public String getColumnName() {
        return columnName;
    }

    public void setcolumnName(String columnName) {
        this.columnName = columnName;
    }

    @Basic
    @Column(name = "column_type" )
    public String getColumnType() {
        return columnType;
    }

    public void setcolumnType(String columnType) {
        this.columnType = columnType;
    }

    @Basic
    @Column(name = "new_value"  ,nullable = false )
    public String getNewValue() {
        return newValue;
    }

    public void setnewValue(String newValue) {
        this.newValue = newValue;
    }

    @Basic
    @Column(name = "old_value"  ,nullable = false )
    public String getOldValue() {
        return oldValue;
    }

    public void setoldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    @Basic
    @Column(name = "column_info"  ,nullable = false )
    public String getColumnInfo() {
        return columnInfo;
    }

    public void setcolumnInfo(String columnInfo) {
        this.columnInfo = columnInfo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogValueEntity that = (LogValueEntity) o;

        return Objects.equals(logValueId, that.logValueId) &&Objects.equals(logTableId, that.logTableId) &&(columnName != null ? columnName.equals(that.columnName) : that.columnName == null) &&(columnType != null ? columnType.equals(that.columnType) : that.columnType == null) &&(newValue != null ? newValue.equals(that.newValue) : that.newValue == null) &&(oldValue != null ? oldValue.equals(that.oldValue) : that.oldValue == null) &&(columnInfo != null ? columnInfo.equals(that.columnInfo) : that.columnInfo == null) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + logValueId;
        result = 31 * result + logTableId;
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + (columnType != null ? columnType.hashCode() : 0);
        result = 31 * result + (newValue != null ? newValue.hashCode() : 0);
        result = 31 * result + (oldValue != null ? oldValue.hashCode() : 0);
        result = 31 * result + (columnInfo != null ? columnInfo.hashCode() : 0);
        return result;
    }
}
