package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_resources",  catalog = "agile_db")
public class SysResourcesEntity implements Serializable {
    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private Integer sysResourcesId;
    //资源类型
    private String resourceType;
    //资源名称
    private String resourceName;
    //资源描述
    private String resourceDesc;
    //资源路径
    private String resourcePath;
    //优先级
    private String pRIORITY;
    //是否可用
    private Boolean eNABLE;
    //是否系统权限
    private Boolean iSSYS;
    //模块
    private Integer moduleId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_RESOURCES_ID" )
    public Integer getSysResourcesId() {
        return sysResourcesId;
    }

    public void setsysResourcesId(int sysResourcesId) {
        this.sysResourcesId = sysResourcesId;
    }

    @Basic
    @Column(name = "RESOURCE_TYPE"  ,nullable = false )
    public String getResourceType() {
        return resourceType;
    }

    public void setresourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Basic
    @Column(name = "RESOURCE_NAME"  ,nullable = false )
    public String getResourceName() {
        return resourceName;
    }

    public void setresourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Basic
    @Column(name = "RESOURCE_DESC"  ,nullable = false )
    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setresourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    @Basic
    @Column(name = "RESOURCE_PATH"  ,nullable = false )
    public String getResourcePath() {
        return resourcePath;
    }

    public void setresourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Basic
    @Column(name = "PRIORITY"  ,nullable = false )
    public String getPRIORITY() {
        return pRIORITY;
    }

    public void setpRIORITY(String pRIORITY) {
        this.pRIORITY = pRIORITY;
    }

    @Basic
    @Column(name = "ENABLE"  ,nullable = false )
    public Boolean getENABLE() {
        return eNABLE;
    }

    public void seteNABLE(Boolean eNABLE) {
        this.eNABLE = eNABLE;
    }

    @Basic
    @Column(name = "ISSYS"  ,nullable = false )
    public Boolean getISSYS() {
        return iSSYS;
    }

    public void setiSSYS(Boolean iSSYS) {
        this.iSSYS = iSSYS;
    }

    @Basic
    @Column(name = "MODULE_ID"  ,nullable = false )
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

        return Objects.equals(sysResourcesId, that.sysResourcesId) &&(resourceType != null ? resourceType.equals(that.resourceType) : that.resourceType == null) &&(resourceName != null ? resourceName.equals(that.resourceName) : that.resourceName == null) &&(resourceDesc != null ? resourceDesc.equals(that.resourceDesc) : that.resourceDesc == null) &&(resourcePath != null ? resourcePath.equals(that.resourcePath) : that.resourcePath == null) &&(pRIORITY != null ? pRIORITY.equals(that.pRIORITY) : that.pRIORITY == null) &&eNABLE == that.eNABLE &&iSSYS == that.iSSYS &&Objects.equals(moduleId, that.moduleId) ;
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
