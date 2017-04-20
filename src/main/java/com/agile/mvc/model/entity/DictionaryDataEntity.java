package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "dictionary_data",  catalog = "agile_db")
public class DictionaryDataEntity implements Serializable {

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

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "code")
    public Integer getCode() {
        return code;
    }

    public void setcode(int code) {
        this.code = code;
    }

    @Basic
    @Column(name = "dic_code")
    public String getDicCode() {
        return dicCode;
    }

    public void setdicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setvalue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "is_fixed")
    public Boolean getIsFixed() {
        return isFixed;
    }

    public void setisFixed(Boolean isFixed) {
        this.isFixed = isFixed;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryDataEntity that = (DictionaryDataEntity) o;

        if (code != that.code) return false;
        if (dicCode != null ? !dicCode.equals(that.dicCode) : that.dicCode != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (isFixed != that.isFixed) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + code;
        result = 31 * result + (dicCode != null ? dicCode.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (isFixed ? 1 : 0);
        return result;
    }
}
