package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/3/29.
 */
@Entity
@Table(name = "dictionary_data", schema = "agile_db", catalog = "")
public class DictionaryDataEntity {
    private int dictionaryDataId;
    private int dictionaryMainId;
    private String name;
    private String value;
    private boolean isFixed;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "dictionary_data_id")
    public int getDictionaryDataId() {
        return dictionaryDataId;
    }

    public void setDictionaryDataId(int dictionaryDataId) {
        this.dictionaryDataId = dictionaryDataId;
    }

    @Basic
    @Column(name = "dictionary_main_id")
    public int getDictionaryMainId() {
        return dictionaryMainId;
    }

    public void setDictionaryMainId(int dictionaryMainId) {
        this.dictionaryMainId = dictionaryMainId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryDataEntity that = (DictionaryDataEntity) o;

        if (dictionaryDataId != that.dictionaryDataId) return false;
        if (dictionaryMainId != that.dictionaryMainId) return false;
        if (isFixed != that.isFixed) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dictionaryDataId;
        result = 31 * result + dictionaryMainId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (isFixed ? 1 : 0);
        return result;
    }
}
