package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "log_table",  catalog = "agile_db")
public class LogTableEntity implements Serializable {
    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private Integer logTableId;
    //日志标识
    private Integer logMainId;
    //数据库
    private String tableSchema;
    //表名
    private String tableName;
    //操作类型
    private String operationType;
    //操作顺序
    private Integer operationOrder;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "log_table_id" , nullable  = true)
    public Integer getLogTableId() {
        return logTableId;
    }

    public void setlogTableId(int logTableId) {
        this.logTableId = logTableId;
    }

    @Basic
    @Column(name = "log_main_id" , nullable  = true)
    public Integer getLogMainId() {
        return logMainId;
    }

    public void setlogMainId(int logMainId) {
        this.logMainId = logMainId;
    }

    @Basic
    @Column(name = "table_schema" , nullable  = true)
    public String getTableSchema() {
        return tableSchema;
    }

    public void settableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    @Basic
    @Column(name = "table_name" , nullable  = true)
    public String getTableName() {
        return tableName;
    }

    public void settableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "operation_type" , nullable  = true)
    public String getOperationType() {
        return operationType;
    }

    public void setoperationType(String operationType) {
        this.operationType = operationType;
    }

    @Basic
    @Column(name = "operation_order" , nullable  = true)
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

        return Objects.equals(logTableId, that.logTableId) &&Objects.equals(logMainId, that.logMainId) &&(tableSchema != null ? tableSchema.equals(that.tableSchema) : that.tableSchema == null) &&(tableName != null ? tableName.equals(that.tableName) : that.tableName == null) &&(operationType != null ? operationType.equals(that.operationType) : that.operationType == null) &&Objects.equals(operationOrder, that.operationOrder) ;
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
