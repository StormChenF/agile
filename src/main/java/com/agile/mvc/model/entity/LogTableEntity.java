package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/4/17.
 */
@Entity
@Table(name = "log_table", schema = "agile_db", catalog = "")
public class LogTableEntity {
    private int logTableId;
    private int logMainId;
    private String tableName;
    private String operationType;
    private int operationOrder;
    private String tableSchema;

    @Id
    @Column(name = "log_table_id")
    public int getLogTableId() {
        return logTableId;
    }

    public void setLogTableId(int logTableId) {
        this.logTableId = logTableId;
    }

    @Basic
    @Column(name = "log_main_id")
    public int getLogMainId() {
        return logMainId;
    }

    public void setLogMainId(int logMainId) {
        this.logMainId = logMainId;
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
    @Column(name = "operation_type")
    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @Basic
    @Column(name = "operation_order")
    public int getOperationOrder() {
        return operationOrder;
    }

    public void setOperationOrder(int operationOrder) {
        this.operationOrder = operationOrder;
    }

    @Basic
    @Column(name = "table_schema")
    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogTableEntity that = (LogTableEntity) o;

        if (logTableId != that.logTableId) return false;
        if (logMainId != that.logMainId) return false;
        if (operationOrder != that.operationOrder) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (operationType != null ? !operationType.equals(that.operationType) : that.operationType != null)
            return false;
        if (tableSchema != null ? !tableSchema.equals(that.tableSchema) : that.tableSchema != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logTableId;
        result = 31 * result + logMainId;
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (operationType != null ? operationType.hashCode() : 0);
        result = 31 * result + operationOrder;
        result = 31 * result + (tableSchema != null ? tableSchema.hashCode() : 0);
        return result;
    }
}
