package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/3/29.
 */
@Entity
@Table(name = "dictionary_main", schema = "agile_db", catalog = "")
public class DictionaryMainEntity {
    private int dictionaryMainId;
    private String code;
    private String name;

    @Id
    @Column(name = "dictionary_main_id")
    public int getDictionaryMainId() {
        return dictionaryMainId;
    }

    public void setDictionaryMainId(int dictionaryMainId) {
        this.dictionaryMainId = dictionaryMainId;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryMainEntity that = (DictionaryMainEntity) o;

        if (dictionaryMainId != that.dictionaryMainId) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dictionaryMainId;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
