package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/4/17.
 */
@Entity
@Table(name = "sys_table", schema = "agile_db", catalog = "")
public class SysTableEntity {
    private int tableSchema;
    private String tableName;
    private String columnName;
    private String columnType;
    private String columnInfo;

    @Id
    @Column(name = "table_schema")
    public int getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(int tableSchema) {
        this.tableSchema = tableSchema;
    }

    @Basic
    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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
    @Column(name = "column_info")
    public String getColumnInfo() {
        return columnInfo;
    }

    public void setColumnInfo(String columnInfo) {
        this.columnInfo = columnInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysTableEntity that = (SysTableEntity) o;

        if (tableSchema != that.tableSchema) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (columnName != null ? !columnName.equals(that.columnName) : that.columnName != null) return false;
        if (columnType != null ? !columnType.equals(that.columnType) : that.columnType != null) return false;
        if (columnInfo != null ? !columnInfo.equals(that.columnInfo) : that.columnInfo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tableSchema;
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + (columnType != null ? columnType.hashCode() : 0);
        result = 31 * result + (columnInfo != null ? columnInfo.hashCode() : 0);
        return result;
    }
}
