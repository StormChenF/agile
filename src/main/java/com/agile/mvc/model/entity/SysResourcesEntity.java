package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/3/16.
 */
@Entity
@Table(name = "sys_resources", schema = "agile_db", catalog = "")
public class SysResourcesEntity {
    private int resourceId;
    private String resourceType;
    private String resourceName;
    private String resourceDesc;
    private String resourcePath;
    private String priority;
    private Boolean enable;
    private Boolean issys;
    private Integer moduleId;

    @Id
    @GeneratedValue
    @Column(name = "RESOURCE_ID", nullable = false)
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "RESOURCE_TYPE", nullable = true, length = 100)
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Basic
    @Column(name = "RESOURCE_NAME", nullable = true, length = 100)
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Basic
    @Column(name = "RESOURCE_DESC", nullable = true, length = 200)
    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    @Basic
    @Column(name = "RESOURCE_PATH", nullable = true, length = 200)
    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Basic
    @Column(name = "PRIORITY", nullable = true, length = 100)
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "ENABLE", nullable = true)
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "ISSYS", nullable = true)
    public Boolean getIssys() {
        return issys;
    }

    public void setIssys(Boolean issys) {
        this.issys = issys;
    }

    @Basic
    @Column(name = "MODULE_ID", nullable = true)
    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysResourcesEntity that = (SysResourcesEntity) o;

        if (resourceId != that.resourceId) return false;
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
        int result = resourceId;
        result = 31 * result + (resourceType != null ? resourceType.hashCode() : 0);
        result = 31 * result + (resourceName != null ? resourceName.hashCode() : 0);
        result = 31 * result + (resourceDesc != null ? resourceDesc.hashCode() : 0);
        result = 31 * result + (resourcePath != null ? resourcePath.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (enable != null ? enable.hashCode() : 0);
        result = 31 * result + (issys != null ? issys.hashCode() : 0);
        result = 31 * result + (moduleId != null ? moduleId.hashCode() : 0);
        return result;
    }
}
