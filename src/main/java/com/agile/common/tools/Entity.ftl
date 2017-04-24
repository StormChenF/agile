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
    public ${className}Entity(<#list columnList as property>${property.propertyType} ${property.propertyName}<#if property_has_next>,</#if> </#list>){
        <#list columnList as property>
        this.${property.propertyName} = ${property.propertyName};
        </#list>
    }

<#list columnList as property>
    <#if property.isPrimaryKey == "true">
    @Id
    <#elseif property.columnType == "blob" ||  property.columnType == "text" >
    @Basic(fetch=FetchType.LAZY)
    <#else>
    @Basic
    </#if>
    <#if property.isAutoincrement == "YES">
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    </#if>
    @Column(name = "${property.columnName}" <#if property.nullable == "false"> ,nullable = ${property.nullable} </#if>)
    public ${property.propertyType} ${property.getMethod}() {
        return ${property.propertyName};
    }

    public void ${property.setMethod}(<#if property.propertyType == "Integer" >int <#elseif property.propertyType == "Long" >long <#else>${property.propertyType} </#if>${property.propertyName}) {
        this.${property.propertyName} = ${property.propertyName};
    }

</#list>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ${className}Entity that = (${className}Entity) o;

        return <#list columnList as property><#if property.propertyType == "Integer" ||  property.propertyType == "Double"  ||  property.propertyType == "Float" || property.propertyType == "Long" || property.propertyType == "Short" || property.propertyType == "int" || property.propertyType == "double" || property.propertyType == "float" || property.propertyType == "long" || property.propertyType == "short" >Objects.equals(${property.propertyName}, that.${property.propertyName}) <#if property_has_next>&&</#if><#elseif  property.propertyType == "Date" || property.propertyType == "Boolean" || property.propertyType == "boolean" >${property.propertyName} == that.${property.propertyName} <#if property_has_next>&&</#if><#else>(${property.propertyName} != null ? ${property.propertyName}.equals(that.${property.propertyName}) : that.${property.propertyName} == null) <#if property_has_next>&&</#if></#if></#list>;
    }

    @Override
    public int hashCode() {
        int result = 0;
    <#list columnList as property>
        <#if property.propertyType == "Integer" ||  property.propertyType == "Double"  ||  property.propertyType == "Float" || property.propertyType == "Long" || property.propertyType == "Short" || property.propertyType == "int" || property.propertyType == "double" || property.propertyType == "float" || property.propertyType == "long" || property.propertyType == "short" >
        result = 31 * result + ${property.propertyName};
        <#elseif property.propertyType == "Boolean" || property.propertyType == "boolean">
        result = 31 * result + (${property.propertyName} ? 1 : 0);
        <#else>
        result = 31 * result + (${property.propertyName} != null ? ${property.propertyName}.hashCode() : 0);
        </#if>
    </#list>
        return result;
    }

    @Override
    public String toString() {
        return "${className}Entity{" +
        <#list columnList as property>
        <#if property.propertyType == "Integer" ||  property.propertyType == "Double"  ||  property.propertyType == "Float" || property.propertyType == "Long" || property.propertyType == "Short" || property.propertyType == "Date"  || property.propertyType == "Timestamp" || property.propertyType == "Clob" || property.propertyType == "Blob" || property.propertyType == "int" || property.propertyType == "double" || property.propertyType == "float" || property.propertyType == "long" || property.propertyType == "short" || property.propertyType == "Boolean" || property.propertyType == "boolean">
        "<#if property_index != 0>,</#if>${property.propertyName}=" + ${property.propertyName} +
        <#else>
        "<#if property_index != 0>,</#if>${property.propertyName}=" + ${property.propertyName} + '\'' +
        </#if>
        </#list>
        '}';
    }
}
