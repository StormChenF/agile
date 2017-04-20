package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_resources",  catalog = "agile_db")
public class SysResourcesEntity {

    private Integer sysResourcesId;
    private String resourceType;
    private String resourceName;
    private String resourceDesc;
    private String resourcePath;
    private String pRIORITY;
    private Boolean eNABLE;
    private Boolean iSSYS;
    private Integer moduleId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_RESOURCES_ID")
    public Integer getSysResourcesId() {
        return sysResourcesId;
    }

    public void setsysResourcesId(int sysResourcesId) {
        this.sysResourcesId = sysResourcesId;
    }

    @Basic
    @Column(name = "RESOURCE_TYPE")
    public String getResourceType() {
        return resourceType;
    }

    public void setresourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Basic
    @Column(name = "RESOURCE_NAME")
    public String getResourceName() {
        return resourceName;
    }

    public void setresourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Basic
    @Column(name = "RESOURCE_DESC")
    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setresourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    @Basic
    @Column(name = "RESOURCE_PATH")
    public String getResourcePath() {
        return resourcePath;
    }

    public void setresourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Basic
    @Column(name = "PRIORITY")
    public String getPRIORITY() {
        return pRIORITY;
    }

    public void setpRIORITY(String pRIORITY) {
        this.pRIORITY = pRIORITY;
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
    public Integer getModuleId() {
        return moduleId;
    }

    public void setmoduleId(int moduleId) {
        this.moduleId = moduleId;
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
        if (pRIORITY != null ? !pRIORITY.equals(that.pRIORITY) : that.pRIORITY != null) return false;
        if (eNABLE != that.eNABLE) return false;
        if (iSSYS != that.iSSYS) return false;
        if (moduleId != that.moduleId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + sysResourcesId;
        result = 31 * result + (resourceType != null ? resourceType.hashCode() : 0);
        result = 31 * result + (resourceName != null ? resourceName.hashCode() : 0);
        result = 31 * result + (resourceDesc != null ? resourceDesc.hashCode() : 0);
        result = 31 * result + (resourcePath != null ? resourcePath.hashCode() : 0);
        result = 31 * result + (pRIORITY != null ? pRIORITY.hashCode() : 0);
        result = 31 * result + (eNABLE ? 1 : 0);
        result = 31 * result + (iSSYS ? 1 : 0);
        result = 31 * result + moduleId;
        return result;
    }
}
