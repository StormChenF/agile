package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mydeathtrial on 2017/4/17.
 */
@Entity
@Table(name = "sys_persistent_logins", schema = "agile_db", catalog = "")
@IdClass(SysPersistentLoginsEntityPK.class)
public class SysPersistentLoginsEntity {
    private String username;
    private String series;
    private String token;
    private Timestamp lastUsed;
    private int sysPersistentLoginsId;

    @Basic
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @Column(name = "SERIES")
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Basic
    @Column(name = "TOKEN")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "LAST_USED")
    public Timestamp getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Timestamp lastUsed) {
        this.lastUsed = lastUsed;
    }

    @Id
    @Column(name = "SYS_PERSISTENT_LOGINS_ID")
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

        SysPersistentLoginsEntity that = (SysPersistentLoginsEntity) o;

        if (sysPersistentLoginsId != that.sysPersistentLoginsId) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (series != null ? !series.equals(that.series) : that.series != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (lastUsed != null ? !lastUsed.equals(that.lastUsed) : that.lastUsed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (series != null ? series.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (lastUsed != null ? lastUsed.hashCode() : 0);
        result = 31 * result + sysPersistentLoginsId;
        return result;
    }
}
