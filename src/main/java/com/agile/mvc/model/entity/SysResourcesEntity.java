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
    private int sysResourcesId;
    //资源类型
    private String resourceType;
    //资源名称
    private String resourceName;
    //资源描述
    private String resourceDesc;
    //资源路径
    private String resourcePath;
    //优先级
    private String priority;
    //是否可用
    private boolean enable;
    //是否系统权限
    private boolean issys;
    //模块
    private int moduleId;

    //无参构造器
    public SysResourcesEntity(){}

    //有参构造器
    public SysResourcesEntity(int sysResourcesId,String resourceType,String resourceName,String resourceDesc,String resourcePath,String priority,boolean enable,boolean issys,int moduleId){
        this.sysResourcesId = sysResourcesId;
        this.resourceType = resourceType;
        this.resourceName = resourceName;
        this.resourceDesc = resourceDesc;
        this.resourcePath = resourcePath;
        this.priority = priority;
        this.enable = enable;
        this.issys = issys;
        this.moduleId = moduleId;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_RESOURCES_ID" )
    public int getSysResourcesId() {
        return sysResourcesId;
    }

    public void setsysResourcesId(int sysResourcesId) {
        this.sysResourcesId = sysResourcesId;
    }

    @Basic
    @Column(name = "RESOURCE_TYPE" , nullable = false )
    public String getResourceType() {
        return resourceType;
    }

    public void setresourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Basic
    @Column(name = "RESOURCE_NAME" , nullable = false )
    public String getResourceName() {
        return resourceName;
    }

    public void setresourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Basic
    @Column(name = "RESOURCE_DESC" , nullable = false )
    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setresourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    @Basic
    @Column(name = "RESOURCE_PATH" , nullable = false )
    public String getResourcePath() {
        return resourcePath;
    }

    public void setresourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Basic
    @Column(name = "PRIORITY" , nullable = false )
    public String getPRIORITY() {
        return priority;
    }

    public void setpRIORITY(String priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "ENABLE" , nullable = false )
    public boolean getENABLE() {
        return enable;
    }

    public void seteNABLE(boolean enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "ISSYS" , nullable = false )
    public boolean getISSYS() {
        return issys;
    }

    public void setiSSYS(boolean issys) {
        this.issys = issys;
    }

    @Basic
    @Column(name = "MODULE_ID" , nullable = false )
    public int getModuleId() {
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

        return 
            Objects.equals(sysResourcesId, that.sysResourcesId)  && 
            (resourceType != null ? resourceType.equals(that.resourceType) : that.resourceType == null)  && 
            (resourceName != null ? resourceName.equals(that.resourceName) : that.resourceName == null)  && 
            (resourceDesc != null ? resourceDesc.equals(that.resourceDesc) : that.resourceDesc == null)  && 
            (resourcePath != null ? resourcePath.equals(that.resourcePath) : that.resourcePath == null)  && 
            (priority != null ? priority.equals(that.priority) : that.priority == null)  && 
            enable == that.enable  && 
            issys == that.issys  && 
            Objects.equals(moduleId, that.moduleId) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + sysResourcesId;
        result = 31 * result + (resourceType != null ? resourceType.hashCode() : 0);
        result = 31 * result + (resourceName != null ? resourceName.hashCode() : 0);
        result = 31 * result + (resourceDesc != null ? resourceDesc.hashCode() : 0);
        result = 31 * result + (resourcePath != null ? resourcePath.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (enable ? 1 : 0);
        result = 31 * result + (issys ? 1 : 0);
        result = 31 * result + moduleId;
        return result;
    }

    @Override
    public String toString() {
        return "SysResourcesEntity{" +
        "sysResourcesId=" + sysResourcesId +
        ",resourceType='" + resourceType + '\'' +
        ",resourceName='" + resourceName + '\'' +
        ",resourceDesc='" + resourceDesc + '\'' +
        ",resourcePath='" + resourcePath + '\'' +
        ",priority='" + priority + '\'' +
        ",enable=" + enable +
        ",issys=" + issys +
        ",moduleId=" + moduleId +
        '}';
    }
}
