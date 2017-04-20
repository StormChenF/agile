package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_authorities",  catalog = "agile_db")
public class SysAuthoritiesEntity implements Serializable {

    //唯一标识
    private Integer sysAuthorityId;
    //权限标识
    private String authorityMark;
    //权限名称
    private String authorityName;
    //权限说明
    private String authorityDesc;
    //提示信息
    private String mESSAGE;
    //是否可用
    private Boolean eNABLE;
    //是否系统权限
    private Boolean iSSYS;
    //模块
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

        return Objects.equals(sysAuthorityId, that.sysAuthorityId) &&(authorityMark != null ? authorityMark.equals(that.authorityMark) : that.authorityMark == null) &&(authorityName != null ? authorityName.equals(that.authorityName) : that.authorityName == null) &&(authorityDesc != null ? authorityDesc.equals(that.authorityDesc) : that.authorityDesc == null) &&(mESSAGE != null ? mESSAGE.equals(that.mESSAGE) : that.mESSAGE == null) &&eNABLE == that.eNABLE &&iSSYS == that.iSSYS &&(moduleId != null ? moduleId.equals(that.moduleId) : that.moduleId == null) ;
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
