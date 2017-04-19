package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/4/17.
 */
@Entity
@Table(name = "sys_authorities", schema = "agile_db", catalog = "")
public class SysAuthoritiesEntity {
    private String authorityMark;
    private String authorityName;
    private String authorityDesc;
    private String message;
    private Boolean enable;
    private Boolean issys;
    private String moduleId;
    private int sysAuthorityId;

    @Basic
    @Column(name = "AUTHORITY_MARK")
    public String getAuthorityMark() {
        return authorityMark;
    }

    public void setAuthorityMark(String authorityMark) {
        this.authorityMark = authorityMark;
    }

    @Basic
    @Column(name = "AUTHORITY_NAME")
    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Basic
    @Column(name = "AUTHORITY_DESC")
    public String getAuthorityDesc() {
        return authorityDesc;
    }

    public void setAuthorityDesc(String authorityDesc) {
        this.authorityDesc = authorityDesc;
    }

    @Basic
    @Column(name = "MESSAGE")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "ENABLE")
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "ISSYS")
    public Boolean getIssys() {
        return issys;
    }

    public void setIssys(Boolean issys) {
        this.issys = issys;
    }

    @Basic
    @Column(name = "MODULE_ID")
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Id
    @Column(name = "SYS_AUTHORITY_ID")
    public int getSysAuthorityId() {
        return sysAuthorityId;
    }

    public void setSysAuthorityId(int sysAuthorityId) {
        this.sysAuthorityId = sysAuthorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysAuthoritiesEntity that = (SysAuthoritiesEntity) o;

        if (sysAuthorityId != that.sysAuthorityId) return false;
        if (authorityMark != null ? !authorityMark.equals(that.authorityMark) : that.authorityMark != null)
            return false;
        if (authorityName != null ? !authorityName.equals(that.authorityName) : that.authorityName != null)
            return false;
        if (authorityDesc != null ? !authorityDesc.equals(that.authorityDesc) : that.authorityDesc != null)
            return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (enable != null ? !enable.equals(that.enable) : that.enable != null) return false;
        if (issys != null ? !issys.equals(that.issys) : that.issys != null) return false;
        if (moduleId != null ? !moduleId.equals(that.moduleId) : that.moduleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authorityMark != null ? authorityMark.hashCode() : 0;
        result = 31 * result + (authorityName != null ? authorityName.hashCode() : 0);
        result = 31 * result + (authorityDesc != null ? authorityDesc.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (enable != null ? enable.hashCode() : 0);
        result = 31 * result + (issys != null ? issys.hashCode() : 0);
        result = 31 * result + (moduleId != null ? moduleId.hashCode() : 0);
        result = 31 * result + sysAuthorityId;
        return result;
    }
}
