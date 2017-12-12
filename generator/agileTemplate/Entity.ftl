package ${entityPackage};

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
<#list importList as import>
import ${import}
</#list>

/**
 * Created by 佟盟
 */
@Entity
@Table(name = "${tableName}", <#if schemaName??>schema = "${schemaName}",</#if> catalog = "${catalogName}")
public class ${className}Entity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
<#list columnList as property>
    //${property.remarks}
    private ${property.propertyType} ${property.propertyName};
</#list>

    //无参构造器
    public ${className}Entity(){}

    //有参构造器
    public ${className}Entity(<#list columnList as property>${property.propertyType} ${property.propertyName}<#if property_has_next>,</#if></#list>){
        <#list columnList as property>
        this.${property.propertyName} = ${property.propertyName};
        </#list>
    }

<#list columnList as property>
    <#if property.isPrimaryKey == "true">
    @Id
    <#elseif property.columnType == "blob" || property.columnType == "text" || property.columnType == "clob" >
    @Basic(fetch=FetchType.LAZY)
    <#else>
    @Basic
    </#if>
    <#if property.isAutoincrement == "YES">
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    </#if>
    <#if property.columnType == "blob" || property.columnType == "clob" >
    @Lob
    </#if>
    @Column(name = "${property.columnName}" <#if property.nullable == "false">, nullable = ${property.nullable} </#if>)
    public ${property.propertyType} ${property.getMethod}() {
        return ${property.propertyName};
    }

    public void ${property.setMethod}(<#if property.propertyType == "Integer" >int <#elseif property.propertyType == "Long" >long <#else>${property.propertyType} </#if>${property.propertyName}) {
        this.${property.propertyName} = ${property.propertyName};
    }

</#list>

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ${className}Entity)) return false;
        ${className}Entity that = (${className}Entity) object;
        return <#list columnList as property>Objects.equals(${property.getMethod}(), that.${property.getMethod}())<#if property_has_next> &&
            <#else >;
            </#if></#list>
    }

    @Override
    public int hashCode() {
        return Objects.hash(<#list columnList as property>${property.getMethod}()<#if property_has_next>, </#if></#list>);
    }

    @Override
    public String toString() {
        return "${className}Entity{" +
        <#list columnList as property>
        <#if property.propertyType == "Integer" || property.propertyType == "Double" || property.propertyType == "Float" || property.propertyType == "Long" || property.propertyType == "Short" || property.propertyType == "Date" || property.propertyType == "Timestamp" || property.propertyType == "Clob" || property.propertyType == "Blob" || property.propertyType == "int" || property.propertyType == "double" || property.propertyType == "float" || property.propertyType == "long" || property.propertyType == "short" || property.propertyType == "Boolean" || property.propertyType == "boolean">
        "<#if property_index != 0>,</#if>${property.propertyName}=" + ${property.propertyName} +
        <#else>
        "<#if property_index != 0>,</#if>${property.propertyName}='" + ${property.propertyName} + '\'' +
        </#if>
        </#list>
        '}';
    }
}
