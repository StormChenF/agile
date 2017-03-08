package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by 佟盟 on 2017/1/17.
 */
@Entity
@Table(name = "sys_users", schema = "agile_db", catalog = "")
public class SysUsersEntity {
    private Integer userId;
    private String username;
    private String name;
    private String password;
    private Timestamp dtCreate;
    private Timestamp lastLogin;
    private Timestamp deadline;
    private String loginIp;
    private String vQzjgid;
    private String vQzjgmc;
    private String depId;
    private String depName;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;

    @Id
    @Column(name = "USER_ID", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "USERNAME", nullable = false, length = 100)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "DT_CREATE", nullable = true)
    public Timestamp getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Timestamp dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Basic
    @Column(name = "LAST_LOGIN", nullable = true)
    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "DEADLINE", nullable = true)
    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "LOGIN_IP", nullable = true, length = 100)
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Basic
    @Column(name = "V_QZJGID", nullable = true, length = 100)
    public String getvQzjgid() {
        return vQzjgid;
    }

    public void setvQzjgid(String vQzjgid) {
        this.vQzjgid = vQzjgid;
    }

    @Basic
    @Column(name = "V_QZJGMC", nullable = true, length = 100)
    public String getvQzjgmc() {
        return vQzjgmc;
    }

    public void setvQzjgmc(String vQzjgmc) {
        this.vQzjgmc = vQzjgmc;
    }

    @Basic
    @Column(name = "DEP_ID", nullable = true, length = 100)
    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Basic
    @Column(name = "DEP_NAME", nullable = true, length = 100)
    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Basic
    @Column(name = "ENABLED", nullable = true)
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "ACCOUNT_NON_EXPIRED", nullable = true)
    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Basic
    @Column(name = "ACCOUNT_NON_LOCKED", nullable = true)
    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Basic
    @Column(name = "CREDENTIALS_NON_EXPIRED", nullable = true)
    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysUsersEntity that = (SysUsersEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (dtCreate != null ? !dtCreate.equals(that.dtCreate) : that.dtCreate != null) return false;
        if (lastLogin != null ? !lastLogin.equals(that.lastLogin) : that.lastLogin != null) return false;
        if (deadline != null ? !deadline.equals(that.deadline) : that.deadline != null) return false;
        if (loginIp != null ? !loginIp.equals(that.loginIp) : that.loginIp != null) return false;
        if (vQzjgid != null ? !vQzjgid.equals(that.vQzjgid) : that.vQzjgid != null) return false;
        if (vQzjgmc != null ? !vQzjgmc.equals(that.vQzjgmc) : that.vQzjgmc != null) return false;
        if (depId != null ? !depId.equals(that.depId) : that.depId != null) return false;
        if (depName != null ? !depName.equals(that.depName) : that.depName != null) return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;
        if (accountNonExpired != null ? !accountNonExpired.equals(that.accountNonExpired) : that.accountNonExpired != null)
            return false;
        if (accountNonLocked != null ? !accountNonLocked.equals(that.accountNonLocked) : that.accountNonLocked != null)
            return false;
        if (credentialsNonExpired != null ? !credentialsNonExpired.equals(that.credentialsNonExpired) : that.credentialsNonExpired != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (dtCreate != null ? dtCreate.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        result = 31 * result + (loginIp != null ? loginIp.hashCode() : 0);
        result = 31 * result + (vQzjgid != null ? vQzjgid.hashCode() : 0);
        result = 31 * result + (vQzjgmc != null ? vQzjgmc.hashCode() : 0);
        result = 31 * result + (depId != null ? depId.hashCode() : 0);
        result = 31 * result + (depName != null ? depName.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (accountNonExpired != null ? accountNonExpired.hashCode() : 0);
        result = 31 * result + (accountNonLocked != null ? accountNonLocked.hashCode() : 0);
        result = 31 * result + (credentialsNonExpired != null ? credentialsNonExpired.hashCode() : 0);
        return result;
    }
}
