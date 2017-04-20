package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "log_table",  catalog = "agile_db")
public class LogTableEntity {

    private Integer logTableId;
    private Integer logMainId;
    private String tableSchema;
    private String tableName;
    private String operationType;
    private Integer operationOrder;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "log_table_id")
    public Integer getLogTableId() {
        return logTableId;
    }

    public void setlogTableId(int logTableId) {
        this.logTableId = logTableId;
    }

    @Basic
    @Column(name = "log_main_id")
    public Integer getLogMainId() {
        return logMainId;
    }

    public void setlogMainId(int logMainId) {
        this.logMainId = logMainId;
    }

    @Basic
    @Column(name = "table_schema")
    public String getTableSchema() {
        return tableSchema;
    }

    public void settableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    @Basic
    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    public void settableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "operation_type")
    public String getOperationType() {
        return operationType;
    }

    public void setoperationType(String operationType) {
        this.operationType = operationType;
    }

    @Basic
    @Column(name = "operation_order")
    public Integer getOperationOrder() {
        return operationOrder;
    }

    public void setoperationOrder(int operationOrder) {
        this.operationOrder = operationOrder;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogTableEntity that = (LogTableEntity) o;

        if (logTableId != that.logTableId) return false;
        if (logMainId != that.logMainId) return false;
        if (tableSchema != null ? !tableSchema.equals(that.tableSchema) : that.tableSchema != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (operationType != null ? !operationType.equals(that.operationType) : that.operationType != null) return false;
        if (operationOrder != that.operationOrder) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + logTableId;
        result = 31 * result + logMainId;
        result = 31 * result + (tableSchema != null ? tableSchema.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (operationType != null ? operationType.hashCode() : 0);
        result = 31 * result + operationOrder;
        return result;
    }
}
