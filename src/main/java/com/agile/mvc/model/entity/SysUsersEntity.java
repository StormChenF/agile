package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Date;
import java.util.Date;
import java.util.Date;

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
    private String username;
    //用户姓名
    private String name;
    //密码
    private String password;
    //创建日期
    private Date dtCreate;
    //最后登录日期
    private Date lastLogin;
    //截止日期
    private Date deadline;
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
    private Boolean enabled;
    //用户是否过期
    private Boolean accountNonExpired;
    //用户是否锁定
    private Boolean accountNonLocked;
    //用户证书是否有效
    private Boolean credentialsNonExpired;

    //无参构造器
    public SysUsersEntity(){}

    //有参构造器
    public SysUsersEntity(Integer sysUsersId,String username,String name,String password,Date dtCreate,Date lastLogin,Date deadline,String loginIp,String vQzjgid,String vQzjgmc,String depId,String depName,Boolean enabled,Boolean accountNonExpired,Boolean accountNonLocked,Boolean credentialsNonExpired){
        this.sysUsersId = sysUsersId;
        this.username = username;
        this.name = name;
        this.password = password;
        this.dtCreate = dtCreate;
        this.lastLogin = lastLogin;
        this.deadline = deadline;
        this.loginIp = loginIp;
        this.vQzjgid = vQzjgid;
        this.vQzjgmc = vQzjgmc;
        this.depId = depId;
        this.depName = depName;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "sys_users_id" , nullable = false )
    public Integer getSysUsersId() {
        return sysUsersId;
    }

    public void setSysUsersId(int sysUsersId) {
        this.sysUsersId = sysUsersId;
    }

    @Basic
    @Column(name = "username" , nullable = false )
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "name" )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password" , nullable = false )
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "dt_create" )
    public Date getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Date dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Basic
    @Column(name = "last_login" )
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "deadline" )
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "login_ip" )
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Basic
    @Column(name = "v_qzjgid" )
    public String getVQzjgid() {
        return vQzjgid;
    }

    public void setVQzjgid(String vQzjgid) {
        this.vQzjgid = vQzjgid;
    }

    @Basic
    @Column(name = "v_qzjgmc" )
    public String getVQzjgmc() {
        return vQzjgmc;
    }

    public void setVQzjgmc(String vQzjgmc) {
        this.vQzjgmc = vQzjgmc;
    }

    @Basic
    @Column(name = "dep_id" )
    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Basic
    @Column(name = "dep_name" )
    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Basic
    @Column(name = "enabled" )
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "account_non_expired" )
    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Basic
    @Column(name = "account_non_locked" )
    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Basic
    @Column(name = "credentials_non_expired" )
    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysUsersEntity)) return false;
        SysUsersEntity that = (SysUsersEntity) object;
        return Objects.equals(getSysUsersId(), that.getSysUsersId()) &&
            Objects.equals(getUsername(), that.getUsername()) &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getPassword(), that.getPassword()) &&
            Objects.equals(getDtCreate(), that.getDtCreate()) &&
            Objects.equals(getLastLogin(), that.getLastLogin()) &&
            Objects.equals(getDeadline(), that.getDeadline()) &&
            Objects.equals(getLoginIp(), that.getLoginIp()) &&
            Objects.equals(getVQzjgid(), that.getVQzjgid()) &&
            Objects.equals(getVQzjgmc(), that.getVQzjgmc()) &&
            Objects.equals(getDepId(), that.getDepId()) &&
            Objects.equals(getDepName(), that.getDepName()) &&
            Objects.equals(getEnabled(), that.getEnabled()) &&
            Objects.equals(getAccountNonExpired(), that.getAccountNonExpired()) &&
            Objects.equals(getAccountNonLocked(), that.getAccountNonLocked()) &&
            Objects.equals(getCredentialsNonExpired(), that.getCredentialsNonExpired());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysUsersId(), getUsername(), getName(), getPassword(), getDtCreate(), getLastLogin(), getDeadline(), getLoginIp(), getVQzjgid(), getVQzjgmc(), getDepId(), getDepName(), getEnabled(), getAccountNonExpired(), getAccountNonLocked(), getCredentialsNonExpired());
    }

    @Override
    public String toString() {
        return "SysUsersEntity{" +
        "sysUsersId=" + sysUsersId +
        ",username='" + username + '\'' +
        ",name='" + name + '\'' +
        ",password='" + password + '\'' +
        ",dtCreate=" + dtCreate +
        ",lastLogin=" + lastLogin +
        ",deadline=" + deadline +
        ",loginIp='" + loginIp + '\'' +
        ",vQzjgid='" + vQzjgid + '\'' +
        ",vQzjgmc='" + vQzjgmc + '\'' +
        ",depId='" + depId + '\'' +
        ",depName='" + depName + '\'' +
        ",enabled=" + enabled +
        ",accountNonExpired=" + accountNonExpired +
        ",accountNonLocked=" + accountNonLocked +
        ",credentialsNonExpired=" + credentialsNonExpired +
        '}';
    }
}
