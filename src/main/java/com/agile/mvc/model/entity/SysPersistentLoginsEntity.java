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
    private Integer sysPersistentLoginsId;
    //用户名
    private String uSERNAME;
    //序列
    private String sERIES;
    //认证信息
    private String tOKEN;
    //最后时间
    private Timestamp lastUsed;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_PERSISTENT_LOGINS_ID" , nullable  = true)
    public Integer getSysPersistentLoginsId() {
        return sysPersistentLoginsId;
    }

    public void setsysPersistentLoginsId(int sysPersistentLoginsId) {
        this.sysPersistentLoginsId = sysPersistentLoginsId;
    }

    @Basic
    @Column(name = "USERNAME" , nullable  = false)
    public String getUSERNAME() {
        return uSERNAME;
    }

    public void setuSERNAME(String uSERNAME) {
        this.uSERNAME = uSERNAME;
    }

    @Basic
    @Column(name = "SERIES" , nullable  = true)
    public String getSERIES() {
        return sERIES;
    }

    public void setsERIES(String sERIES) {
        this.sERIES = sERIES;
    }

    @Basic
    @Column(name = "TOKEN" , nullable  = false)
    public String getTOKEN() {
        return tOKEN;
    }

    public void settOKEN(String tOKEN) {
        this.tOKEN = tOKEN;
    }

    @Basic
    @Column(name = "LAST_USED" , nullable  = true)
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

        return Objects.equals(sysPersistentLoginsId, that.sysPersistentLoginsId) &&(uSERNAME != null ? uSERNAME.equals(that.uSERNAME) : that.uSERNAME == null) &&(sERIES != null ? sERIES.equals(that.sERIES) : that.sERIES == null) &&(tOKEN != null ? tOKEN.equals(that.tOKEN) : that.tOKEN == null) &&(lastUsed != null ? lastUsed.equals(that.lastUsed) : that.lastUsed == null) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + sysPersistentLoginsId;
        result = 31 * result + (uSERNAME != null ? uSERNAME.hashCode() : 0);
        result = 31 * result + (sERIES != null ? sERIES.hashCode() : 0);
        result = 31 * result + (tOKEN != null ? tOKEN.hashCode() : 0);
        result = 31 * result + (lastUsed != null ? lastUsed.hashCode() : 0);
        return result;
    }
}
