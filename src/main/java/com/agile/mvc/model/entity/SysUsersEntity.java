package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Date;
import java.sql.Date;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_users",  catalog = "agile_db")
public class SysUsersEntity {

    private Integer sysUsersId;
    private String uSERNAME;
    private String nAME;
    private String pASSWORD;
    private Date dtCreate;
    private Date lastLogin;
    private Date dEADLINE;
    private String loginIp;
    private String vQzjgid;
    private String vQzjgmc;
    private String depId;
    private String depName;
    private Boolean eNABLED;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_USERS_ID")
    public Integer getSysUsersId() {
        return sysUsersId;
    }

    public void setsysUsersId(int sysUsersId) {
        this.sysUsersId = sysUsersId;
    }

    @Basic
    @Column(name = "USERNAME")
    public String getUSERNAME() {
        return uSERNAME;
    }

    public void setuSERNAME(String uSERNAME) {
        this.uSERNAME = uSERNAME;
    }

    @Basic
    @Column(name = "NAME")
    public String getNAME() {
        return nAME;
    }

    public void setnAME(String nAME) {
        this.nAME = nAME;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPASSWORD() {
        return pASSWORD;
    }

    public void setpASSWORD(String pASSWORD) {
        this.pASSWORD = pASSWORD;
    }

    @Basic
    @Column(name = "DT_CREATE")
    public Date getDtCreate() {
        return dtCreate;
    }

    public void setdtCreate(Date dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Basic
    @Column(name = "LAST_LOGIN")
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setlastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "DEADLINE")
    public Date getDEADLINE() {
        return dEADLINE;
    }

    public void setdEADLINE(Date dEADLINE) {
        this.dEADLINE = dEADLINE;
    }

    @Basic
    @Column(name = "LOGIN_IP")
    public String getLoginIp() {
        return loginIp;
    }

    public void setloginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Basic
    @Column(name = "V_QZJGID")
    public String getVQzjgid() {
        return vQzjgid;
    }

    public void setvQzjgid(String vQzjgid) {
        this.vQzjgid = vQzjgid;
    }

    @Basic
    @Column(name = "V_QZJGMC")
    public String getVQzjgmc() {
        return vQzjgmc;
    }

    public void setvQzjgmc(String vQzjgmc) {
        this.vQzjgmc = vQzjgmc;
    }

    @Basic
    @Column(name = "DEP_ID")
    public String getDepId() {
        return depId;
    }

    public void setdepId(String depId) {
        this.depId = depId;
    }

    @Basic
    @Column(name = "DEP_NAME")
    public String getDepName() {
        return depName;
    }

    public void setdepName(String depName) {
        this.depName = depName;
    }

    @Basic
    @Column(name = "ENABLED")
    public Boolean getENABLED() {
        return eNABLED;
    }

    public void seteNABLED(Boolean eNABLED) {
        this.eNABLED = eNABLED;
    }

    @Basic
    @Column(name = "ACCOUNT_NON_EXPIRED")
    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setaccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Basic
    @Column(name = "ACCOUNT_NON_LOCKED")
    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setaccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Basic
    @Column(name = "CREDENTIALS_NON_EXPIRED")
    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setcredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysUsersEntity that = (SysUsersEntity) o;

        if (sysUsersId != that.sysUsersId) return false;
        if (uSERNAME != null ? !uSERNAME.equals(that.uSERNAME) : that.uSERNAME != null) return false;
        if (nAME != null ? !nAME.equals(that.nAME) : that.nAME != null) return false;
        if (pASSWORD != null ? !pASSWORD.equals(that.pASSWORD) : that.pASSWORD != null) return false;
        if (dtCreate != that.dtCreate) return false;
        if (lastLogin != that.lastLogin) return false;
        if (dEADLINE != that.dEADLINE) return false;
        if (loginIp != null ? !loginIp.equals(that.loginIp) : that.loginIp != null) return false;
        if (vQzjgid != null ? !vQzjgid.equals(that.vQzjgid) : that.vQzjgid != null) return false;
        if (vQzjgmc != null ? !vQzjgmc.equals(that.vQzjgmc) : that.vQzjgmc != null) return false;
        if (depId != null ? !depId.equals(that.depId) : that.depId != null) return false;
        if (depName != null ? !depName.equals(that.depName) : that.depName != null) return false;
        if (eNABLED != that.eNABLED) return false;
        if (accountNonExpired != that.accountNonExpired) return false;
        if (accountNonLocked != that.accountNonLocked) return false;
        if (credentialsNonExpired != that.credentialsNonExpired) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + sysUsersId;
        result = 31 * result + (uSERNAME != null ? uSERNAME.hashCode() : 0);
        result = 31 * result + (nAME != null ? nAME.hashCode() : 0);
        result = 31 * result + (pASSWORD != null ? pASSWORD.hashCode() : 0);
        result = 31 * result + (dtCreate != null ? dtCreate.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        result = 31 * result + (dEADLINE != null ? dEADLINE.hashCode() : 0);
        result = 31 * result + (loginIp != null ? loginIp.hashCode() : 0);
        result = 31 * result + (vQzjgid != null ? vQzjgid.hashCode() : 0);
        result = 31 * result + (vQzjgmc != null ? vQzjgmc.hashCode() : 0);
        result = 31 * result + (depId != null ? depId.hashCode() : 0);
        result = 31 * result + (depName != null ? depName.hashCode() : 0);
        result = 31 * result + (eNABLED ? 1 : 0);
        result = 31 * result + (accountNonExpired ? 1 : 0);
        result = 31 * result + (accountNonLocked ? 1 : 0);
        result = 31 * result + (credentialsNonExpired ? 1 : 0);
        return result;
    }
}
