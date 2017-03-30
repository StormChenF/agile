package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mydeathtrial on 2017/3/20.
 */
@Entity
@Table(name = "sys_persistent_logins", schema = "agile_db", catalog = "")
@IdClass(SysPersistentLoginsEntityPK.class)
public class SysPersistentLoginsEntity {
    private int id;
    private String username;
    private String series;
    private String token;
    private Timestamp lastUsed;
    private int sysPersistentLoginsId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_PERSISTENT_LOGINS_ID")
    public int getSysPersistentLoginsId() {
        return sysPersistentLoginsId;
    }

    public void setSysPersistentLoginsId(int sysPersistentLoginsId) {
        this.sysPersistentLoginsId = sysPersistentLoginsId;
    }

    @Basic
    @Column(name = "USERNAME", nullable = true, length = 100)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @Basic
    @Column(name = "SERIES", nullable = false, length = 64)
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Basic
    @Column(name = "TOKEN", nullable = true, length = 64)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "LAST_USED", nullable = false)
    public Timestamp getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Timestamp lastUsed) {
        this.lastUsed = lastUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysPersistentLoginsEntity that = (SysPersistentLoginsEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (series != null ? !series.equals(that.series) : that.series != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (lastUsed != null ? !lastUsed.equals(that.lastUsed) : that.lastUsed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (series != null ? series.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (lastUsed != null ? lastUsed.hashCode() : 0);
        return result;
    }
}
