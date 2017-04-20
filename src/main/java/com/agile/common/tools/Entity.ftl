package ${entityPackage};

import javax.persistence.*;
<#list importList as import>
import ${import}
</#list>

/**
* Created by 佟盟
*/
@Entity
@Table(name = "${tableName}", <#if schemaName??>schema = "${schemaName}",</#if> catalog = "${catalogName}")
public class ${className}Entity {

<#list columnList as property>
    private ${property.propertyType} ${property.propertyName};
</#list>

<#list columnList as property>
    <#if property.isPrimaryKey == "true">
    @Id
    <#else>
    @Basic
    </#if>
    <#if property.isAutoincrement == "YES">
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    </#if>
    @Column(name = "${property.columnName}")
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

    <#list columnList as property>
        <#if property.propertyType == "String">
        if (${property.propertyName} != null ? !${property.propertyName}.equals(that.${property.propertyName}) : that.${property.propertyName} != null) return false;
        <#else>
        if (${property.propertyName} != that.${property.propertyName}) return false;
        </#if>
    </#list>
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
    <#list columnList as property>
        <#if property.propertyType == "Integer" ||  property.propertyType == "Double"  ||  property.propertyType == "Float">
        result = 31 * result + ${property.propertyName};
        <#elseif property.propertyType == "Boolean">
        result = 31 * result + (${property.propertyName} ? 1 : 0);
        <#else>
        result = 31 * result + (${property.propertyName} != null ? ${property.propertyName}.hashCode() : 0);
        </#if>
    </#list>
        return result;
    }
}
