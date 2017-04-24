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
    private int logTableId;
    //日志标识
    private int logMainId;
    //数据库
    private String tableSchema;
    //表名
    private String tableName;
    //操作类型
    private String operationType;
    //操作顺序
    private int operationOrder;

    //无参构造器
    public LogTableEntity(){}

    //有参构造器
    public LogTableEntity(int logTableId, int logMainId, String tableSchema, String tableName, String operationType, int operationOrder ){
        this.logTableId = logTableId;
        this.logMainId = logMainId;
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        this.operationType = operationType;
        this.operationOrder = operationOrder;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "log_table_id" )
    public int getLogTableId() {
        return logTableId;
    }

    public void setlogTableId(int logTableId) {
        this.logTableId = logTableId;
    }

    @Basic
    @Column(name = "log_main_id" )
    public int getLogMainId() {
        return logMainId;
    }

    public void setlogMainId(int logMainId) {
        this.logMainId = logMainId;
    }

    @Basic
    @Column(name = "table_schema" )
    public String getTableSchema() {
        return tableSchema;
    }

    public void settableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    @Basic
    @Column(name = "table_name" )
    public String getTableName() {
        return tableName;
    }

    public void settableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "operation_type" )
    public String getOperationType() {
        return operationType;
    }

    public void setoperationType(String operationType) {
        this.operationType = operationType;
    }

    @Basic
    @Column(name = "operation_order" )
    public int getOperationOrder() {
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

    @Override
    public String toString() {
        return "LogTableEntity{" +
        "logTableId=" + logTableId +
        ",logMainId=" + logMainId +
        ",tableSchema=" + tableSchema + '\'' +
        ",tableName=" + tableName + '\'' +
        ",operationType=" + operationType + '\'' +
        ",operationOrder=" + operationOrder +
        '}';
    }
}
