package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.sql.Date;
import java.sql.Date;
import java.sql.Date;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_users",  catalog = "agile_db")
public class SysUsersEntity implements Serializable {
    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private Integer sysUsersId;
    //用户名
    private String uSERNAME;
    //用户姓名
    private String nAME;
    //密码
    private String pASSWORD;
    //创建日期
    private Date dtCreate;
    //最后登录日期
    private Date lastLogin;
    //截止日期
    private Date dEADLINE;
    //最后登录IP地址
    private String loginIp;
    //所属机构ID
    private String vQzjgid;
    //所属机构名称
    private String vQzjgmc;
    //地区编号
    private String depId;
    //地区名称
    private String depName;
    //是否可用
    private Boolean eNABLED;
    //用户是否过期
    private Boolean accountNonExpired;
    //用户是否锁定
    private Boolean accountNonLocked;
    //用户证书是否有效
    private Boolean credentialsNonExpired;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_USERS_ID" , nullable  = true)
    public Integer getSysUsersId() {
        return sysUsersId;
    }

    public void setsysUsersId(int sysUsersId) {
        this.sysUsersId = sysUsersId;
    }

    @Basic
    @Column(name = "USERNAME" , nullable  = true)
    public String getUSERNAME() {
        return uSERNAME;
    }

    public void setuSERNAME(String uSERNAME) {
        this.uSERNAME = uSERNAME;
    }

    @Basic
    @Column(name = "NAME" , nullable  = false)
    public String getNAME() {
        return nAME;
    }

    public void setnAME(String nAME) {
        this.nAME = nAME;
    }

    @Basic
    @Column(name = "PASSWORD" , nullable  = true)
    public String getPASSWORD() {
        return pASSWORD;
    }

    public void setpASSWORD(String pASSWORD) {
        this.pASSWORD = pASSWORD;
    }

    @Basic
    @Column(name = "DT_CREATE" , nullable  = false)
    public Date getDtCreate() {
        return dtCreate;
    }

    public void setdtCreate(Date dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Basic
    @Column(name = "LAST_LOGIN" , nullable  = false)
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setlastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "DEADLINE" , nullable  = false)
    public Date getDEADLINE() {
        return dEADLINE;
    }

    public void setdEADLINE(Date dEADLINE) {
        this.dEADLINE = dEADLINE;
    }

    @Basic
    @Column(name = "LOGIN_IP" , nullable  = false)
    public String getLoginIp() {
        return loginIp;
    }

    public void setloginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Basic
    @Column(name = "V_QZJGID" , nullable  = false)
    public String getVQzjgid() {
        return vQzjgid;
    }

    public void setvQzjgid(String vQzjgid) {
        this.vQzjgid = vQzjgid;
    }

    @Basic
    @Column(name = "V_QZJGMC" , nullable  = false)
    public String getVQzjgmc() {
        return vQzjgmc;
    }

    public void setvQzjgmc(String vQzjgmc) {
        this.vQzjgmc = vQzjgmc;
    }

    @Basic
    @Column(name = "DEP_ID" , nullable  = false)
    public String getDepId() {
        return depId;
    }

    public void setdepId(String depId) {
        this.depId = depId;
    }

    @Basic
    @Column(name = "DEP_NAME" , nullable  = false)
    public String getDepName() {
        return depName;
    }

    public void setdepName(String depName) {
        this.depName = depName;
    }

    @Basic
    @Column(name = "ENABLED" , nullable  = false)
    public Boolean getENABLED() {
        return eNABLED;
    }

    public void seteNABLED(Boolean eNABLED) {
        this.eNABLED = eNABLED;
    }

    @Basic
    @Column(name = "ACCOUNT_NON_EXPIRED" , nullable  = false)
    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setaccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Basic
    @Column(name = "ACCOUNT_NON_LOCKED" , nullable  = false)
    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setaccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Basic
    @Column(name = "CREDENTIALS_NON_EXPIRED" , nullable  = false)
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

        return Objects.equals(sysUsersId, that.sysUsersId) &&(uSERNAME != null ? uSERNAME.equals(that.uSERNAME) : that.uSERNAME == null) &&(nAME != null ? nAME.equals(that.nAME) : that.nAME == null) &&(pASSWORD != null ? pASSWORD.equals(that.pASSWORD) : that.pASSWORD == null) &&dtCreate == that.dtCreate &&lastLogin == that.lastLogin &&dEADLINE == that.dEADLINE &&(loginIp != null ? loginIp.equals(that.loginIp) : that.loginIp == null) &&(vQzjgid != null ? vQzjgid.equals(that.vQzjgid) : that.vQzjgid == null) &&(vQzjgmc != null ? vQzjgmc.equals(that.vQzjgmc) : that.vQzjgmc == null) &&(depId != null ? depId.equals(that.depId) : that.depId == null) &&(depName != null ? depName.equals(that.depName) : that.depName == null) &&eNABLED == that.eNABLED &&accountNonExpired == that.accountNonExpired &&accountNonLocked == that.accountNonLocked &&credentialsNonExpired == that.credentialsNonExpired ;
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
