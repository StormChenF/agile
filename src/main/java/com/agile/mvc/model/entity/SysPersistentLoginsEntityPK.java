package com.agile.mvc.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by mydeathtrial on 2017/3/16.
 */
public class SysPersistentLoginsEntityPK implements Serializable {
    private int id;
    private String series;

    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "SERIES", nullable = false, length = 64)
    @Id
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysPersistentLoginsEntityPK that = (SysPersistentLoginsEntityPK) o;

        if (id != that.id) return false;
        if (series != null ? !series.equals(that.series) : that.series != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (series != null ? series.hashCode() : 0);
        return result;
    }
}
