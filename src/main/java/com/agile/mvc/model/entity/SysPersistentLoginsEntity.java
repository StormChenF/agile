package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.sql.Timestamp;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_persistent_logins",  catalog = "agile_db")
public class SysPersistentLoginsEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private int sysPersistentLoginsId;
    //用户名
    private String username;
    //序列
    private String series;
    //认证信息
    private String token;
    //最后时间
    private Timestamp lastUsed;

    //无参构造器
    public SysPersistentLoginsEntity(){}

    //有参构造器
    public SysPersistentLoginsEntity(int sysPersistentLoginsId, String username, String series, String token, Timestamp lastUsed ){
        this.sysPersistentLoginsId = sysPersistentLoginsId;
        this.username = username;
        this.series = series;
        this.token = token;
        this.lastUsed = lastUsed;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_PERSISTENT_LOGINS_ID" )
    public int getSysPersistentLoginsId() {
        return sysPersistentLoginsId;
    }

    public void setsysPersistentLoginsId(int sysPersistentLoginsId) {
        this.sysPersistentLoginsId = sysPersistentLoginsId;
    }

    @Basic
    @Column(name = "USERNAME"  ,nullable = false )
    public String getUSERNAME() {
        return username;
    }

    public void setuSERNAME(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "SERIES" )
    public String getSERIES() {
        return series;
    }

    public void setsERIES(String series) {
        this.series = series;
    }

    @Basic
    @Column(name = "TOKEN"  ,nullable = false )
    public String getTOKEN() {
        return token;
    }

    public void settOKEN(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "LAST_USED" )
    public Timestamp getLastUsed() {
        return lastUsed;
    }

    public void setlastUsed(Timestamp lastUsed) {
        this.lastUsed = lastUsed;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysPersistentLoginsEntity that = (SysPersistentLoginsEntity) o;

        return Objects.equals(sysPersistentLoginsId, that.sysPersistentLoginsId) &&(username != null ? username.equals(that.username) : that.username == null) &&(series != null ? series.equals(that.series) : that.series == null) &&(token != null ? token.equals(that.token) : that.token == null) &&(lastUsed != null ? lastUsed.equals(that.lastUsed) : that.lastUsed == null) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + sysPersistentLoginsId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (series != null ? series.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (lastUsed != null ? lastUsed.hashCode() : 0);
        return result;
    }
}
