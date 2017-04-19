package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/4/17.
 */
@Entity
@Table(name = "sys_resources", schema = "agile_db", catalog = "")
public class SysResourcesEntity {
    private String resourceType;
    private String resourceName;
    private String resourceDesc;
    private String resourcePath;
    private String priority;
    private Boolean enable;
    private Boolean issys;
    private Integer moduleId;
    private int sysResourcesId;

    @Basic
    @Column(name = "RESOURCE_TYPE")
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Basic
    @Column(name = "RESOURCE_NAME")
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Basic
    @Column(name = "RESOURCE_DESC")
    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    @Basic
    @Column(name = "RESOURCE_PATH")
    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Basic
    @Column(name = "PRIORITY")
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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
    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    @Id
    @Column(name = "SYS_RESOURCES_ID")
    public int getSysResourcesId() {
        return sysResourcesId;
    }

    public void setSysResourcesId(int sysResourcesId) {
        this.sysResourcesId = sysResourcesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysResourcesEntity that = (SysResourcesEntity) o;

        if (sysResourcesId != that.sysResourcesId) return false;
        if (resourceType != null ? !resourceType.equals(that.resourceType) : that.resourceType != null) return false;
        if (resourceName != null ? !resourceName.equals(that.resourceName) : that.resourceName != null) return false;
        if (resourceDesc != null ? !resourceDesc.equals(that.resourceDesc) : that.resourceDesc != null) return false;
        if (resourcePath != null ? !resourcePath.equals(that.resourcePath) : that.resourcePath != null) return false;
        if (priority != null ? !priority.equals(that.priority) : that.priority != null) return false;
        if (enable != null ? !enable.equals(that.enable) : that.enable != null) return false;
        if (issys != null ? !issys.equals(that.issys) : that.issys != null) return false;
        if (moduleId != null ? !moduleId.equals(that.moduleId) : that.moduleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = resourceType != null ? resourceType.hashCode() : 0;
        result = 31 * result + (resourceName != null ? resourceName.hashCode() : 0);
        result = 31 * result + (resourceDesc != null ? resourceDesc.hashCode() : 0);
        result = 31 * result + (resourcePath != null ? resourcePath.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (enable != null ? enable.hashCode() : 0);
        result = 31 * result + (issys != null ? issys.hashCode() : 0);
        result = 31 * result + (moduleId != null ? moduleId.hashCode() : 0);
        result = 31 * result + sysResourcesId;
        return result;
    }
}
