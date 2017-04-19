package com.agile.mvc.model.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by mydeathtrial on 2017/4/17.
 */
public class SysPersistentLoginsEntityPK implements Serializable {
    private String series;
    private int sysPersistentLoginsId;

    @Column(name = "SERIES")
    @Id
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Column(name = "SYS_PERSISTENT_LOGINS_ID")
    @Id
    public int getSysPersistentLoginsId() {
        return sysPersistentLoginsId;
    }

    public void setSysPersistentLoginsId(int sysPersistentLoginsId) {
        this.sysPersistentLoginsId = sysPersistentLoginsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysPersistentLoginsEntityPK that = (SysPersistentLoginsEntityPK) o;

        if (sysPersistentLoginsId != that.sysPersistentLoginsId) return false;
        if (series != null ? !series.equals(that.series) : that.series != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = series != null ? series.hashCode() : 0;
        result = 31 * result + sysPersistentLoginsId;
        return result;
    }
}
