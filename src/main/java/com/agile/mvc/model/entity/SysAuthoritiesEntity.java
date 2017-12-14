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

    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private Integer sysAuthorityId;
    //权限标识
    private String authorityMark;
    //权限名称
    private String authorityName;
    //权限说明
    private String authorityDesc;
    //提示信息
    private String message;
    //是否可用
    private Boolean enable;
    //是否系统权限
    private Boolean issys;
    //模块
    private String moduleId;

    //无参构造器
    public SysAuthoritiesEntity(){}

    //有参构造器
    public SysAuthoritiesEntity(Integer sysAuthorityId,String authorityMark,String authorityName,String authorityDesc,String message,Boolean enable,Boolean issys,String moduleId){
        this.sysAuthorityId = sysAuthorityId;
        this.authorityMark = authorityMark;
        this.authorityName = authorityName;
        this.authorityDesc = authorityDesc;
        this.message = message;
        this.enable = enable;
        this.issys = issys;
        this.moduleId = moduleId;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "sys_authority_id" , nullable = false )
    public Integer getSysAuthorityId() {
        return sysAuthorityId;
    }

    public void setSysAuthorityId(int sysAuthorityId) {
        this.sysAuthorityId = sysAuthorityId;
    }

    @Basic
    @Column(name = "authority_mark" )
    public String getAuthorityMark() {
        return authorityMark;
    }

    public void setAuthorityMark(String authorityMark) {
        this.authorityMark = authorityMark;
    }

    @Basic
    @Column(name = "authority_name" , nullable = false )
    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Basic
    @Column(name = "authority_desc" )
    public String getAuthorityDesc() {
        return authorityDesc;
    }

    public void setAuthorityDesc(String authorityDesc) {
        this.authorityDesc = authorityDesc;
    }

    @Basic
    @Column(name = "message" )
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "enable" )
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "issys" )
    public Boolean getIssys() {
        return issys;
    }

    public void setIssys(Boolean issys) {
        this.issys = issys;
    }

    @Basic
    @Column(name = "module_id" )
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysAuthoritiesEntity)) return false;
        SysAuthoritiesEntity that = (SysAuthoritiesEntity) object;
        return Objects.equals(getSysAuthorityId(), that.getSysAuthorityId()) &&
            Objects.equals(getAuthorityMark(), that.getAuthorityMark()) &&
            Objects.equals(getAuthorityName(), that.getAuthorityName()) &&
            Objects.equals(getAuthorityDesc(), that.getAuthorityDesc()) &&
            Objects.equals(getMessage(), that.getMessage()) &&
            Objects.equals(getEnable(), that.getEnable()) &&
            Objects.equals(getIssys(), that.getIssys()) &&
            Objects.equals(getModuleId(), that.getModuleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysAuthorityId(), getAuthorityMark(), getAuthorityName(), getAuthorityDesc(), getMessage(), getEnable(), getIssys(), getModuleId());
    }

    @Override
    public String toString() {
        return "SysAuthoritiesEntity{" +
        "sysAuthorityId=" + sysAuthorityId +
        ",authorityMark='" + authorityMark + '\'' +
        ",authorityName='" + authorityName + '\'' +
        ",authorityDesc='" + authorityDesc + '\'' +
        ",message='" + message + '\'' +
        ",enable=" + enable +
        ",issys=" + issys +
        ",moduleId='" + moduleId + '\'' +
        '}';
    }
}
