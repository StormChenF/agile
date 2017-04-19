package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/4/17.
 */
@Entity
@Table(name = "dictionary_data", schema = "agile_db", catalog = "")
public class DictionaryDataEntity {
    private String name;
    private String value;
    private boolean isFixed;
    private int code;
    private String dicCode;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "is_fixed")
    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    @Id
    @Column(name = "code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Basic
    @Column(name = "dic_code")
    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryDataEntity that = (DictionaryDataEntity) o;

        if (isFixed != that.isFixed) return false;
        if (code != that.code) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (dicCode != null ? !dicCode.equals(that.dicCode) : that.dicCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (isFixed ? 1 : 0);
        result = 31 * result + code;
        result = 31 * result + (dicCode != null ? dicCode.hashCode() : 0);
        return result;
    }
}
