package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_authorities",  catalog = "agile_db")
public class SysAuthoritiesEntity {

    private Integer sysAuthorityId;
    private String authorityMark;
    private String authorityName;
    private String authorityDesc;
    private String mESSAGE;
    private Boolean eNABLE;
    private Boolean iSSYS;
    private String moduleId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_AUTHORITY_ID")
    public Integer getSysAuthorityId() {
        return sysAuthorityId;
    }

    public void setsysAuthorityId(int sysAuthorityId) {
        this.sysAuthorityId = sysAuthorityId;
    }

    @Basic
    @Column(name = "AUTHORITY_MARK")
    public String getAuthorityMark() {
        return authorityMark;
    }

    public void setauthorityMark(String authorityMark) {
        this.authorityMark = authorityMark;
    }

    @Basic
    @Column(name = "AUTHORITY_NAME")
    public String getAuthorityName() {
        return authorityName;
    }

    public void setauthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Basic
    @Column(name = "AUTHORITY_DESC")
    public String getAuthorityDesc() {
        return authorityDesc;
    }

    public void setauthorityDesc(String authorityDesc) {
        this.authorityDesc = authorityDesc;
    }

    @Basic
    @Column(name = "MESSAGE")
    public String getMESSAGE() {
        return mESSAGE;
    }

    public void setmESSAGE(String mESSAGE) {
        this.mESSAGE = mESSAGE;
    }

    @Basic
    @Column(name = "ENABLE")
    public Boolean getENABLE() {
        return eNABLE;
    }

    public void seteNABLE(Boolean eNABLE) {
        this.eNABLE = eNABLE;
    }

    @Basic
    @Column(name = "ISSYS")
    public Boolean getISSYS() {
        return iSSYS;
    }

    public void setiSSYS(Boolean iSSYS) {
        this.iSSYS = iSSYS;
    }

    @Basic
    @Column(name = "MODULE_ID")
    public String getModuleId() {
        return moduleId;
    }

    public void setmoduleId(String moduleId) {
        this.moduleId = moduleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysAuthoritiesEntity that = (SysAuthoritiesEntity) o;

        if (sysAuthorityId != that.sysAuthorityId) return false;
        if (authorityMark != null ? !authorityMark.equals(that.authorityMark) : that.authorityMark != null) return false;
        if (authorityName != null ? !authorityName.equals(that.authorityName) : that.authorityName != null) return false;
        if (authorityDesc != null ? !authorityDesc.equals(that.authorityDesc) : that.authorityDesc != null) return false;
        if (mESSAGE != null ? !mESSAGE.equals(that.mESSAGE) : that.mESSAGE != null) return false;
        if (eNABLE != that.eNABLE) return false;
        if (iSSYS != that.iSSYS) return false;
        if (moduleId != null ? !moduleId.equals(that.moduleId) : that.moduleId != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + sysAuthorityId;
        result = 31 * result + (authorityMark != null ? authorityMark.hashCode() : 0);
        result = 31 * result + (authorityName != null ? authorityName.hashCode() : 0);
        result = 31 * result + (authorityDesc != null ? authorityDesc.hashCode() : 0);
        result = 31 * result + (mESSAGE != null ? mESSAGE.hashCode() : 0);
        result = 31 * result + (eNABLE ? 1 : 0);
        result = 31 * result + (iSSYS ? 1 : 0);
        result = 31 * result + (moduleId != null ? moduleId.hashCode() : 0);
        return result;
    }
}
