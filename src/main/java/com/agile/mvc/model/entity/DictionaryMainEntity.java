package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/4/17.
 */
@Entity
@Table(name = "dictionary_main", schema = "agile_db", catalog = "")
public class DictionaryMainEntity {
    private int code;
    private String name;
    private boolean isConstant;

    @Id
    @Column(name = "code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    @Basic
    @Column(name = "is_constant")
    public boolean isConstant() {
        return isConstant;
    }

    public void setConstant(boolean constant) {
        isConstant = constant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryMainEntity that = (DictionaryMainEntity) o;

        if (code != that.code) return false;
        if (isConstant != that.isConstant) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isConstant ? 1 : 0);
        return result;
    }
}
