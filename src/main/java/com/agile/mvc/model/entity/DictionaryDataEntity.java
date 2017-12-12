package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by 佟盟
 */
@Entity
@Table(name = "dictionary_data",  catalog = "agile_db")
public class DictionaryDataEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //字典编码
    private Integer code;
    //字典表_字典编码
    private String dicCode;
    //字典值显示名称
    private String name;
    //字典值代表值
    private String value;
    //字典值是否固定
    private Boolean isFixed;

    //无参构造器
    public DictionaryDataEntity(){}

    //有参构造器
    public DictionaryDataEntity(Integer code,String dicCode,String name,String value,Boolean isFixed){
        this.code = code;
        this.dicCode = dicCode;
        this.name = name;
        this.value = value;
        this.isFixed = isFixed;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "code" )
    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Basic
    @Column(name = "dic_code" , nullable = false )
    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    @Basic
    @Column(name = "name" )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "value" )
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "is_fixed" )
    public Boolean getIsFixed() {
        return isFixed;
    }

    public void setIsFixed(Boolean isFixed) {
        this.isFixed = isFixed;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof DictionaryDataEntity)) return false;
        DictionaryDataEntity that = (DictionaryDataEntity) object;
        return Objects.equals(getCode(), that.getCode()) &&
            Objects.equals(getDicCode(), that.getDicCode()) &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getValue(), that.getValue()) &&
            Objects.equals(getIsFixed(), that.getIsFixed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getDicCode(), getName(), getValue(), getIsFixed());
    }

    @Override
    public String toString() {
        return "DictionaryDataEntity{" +
        "code=" + code +
        ",dicCode='" + dicCode + '\'' +
        ",name='" + name + '\'' +
        ",value='" + value + '\'' +
        ",isFixed=" + isFixed +
        '}';
    }
}
