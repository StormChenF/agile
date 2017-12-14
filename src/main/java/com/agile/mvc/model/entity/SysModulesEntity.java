package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by 佟盟
 */
@Entity
@Table(name = "sys_modules",  catalog = "agile_db")
public class SysModulesEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private Integer sysModulesId;
    //模块名称
    private String moduleName;
    //模块说明
    private String moduleDesc;
    //模块类型
    private String moduleType;
    //模块上级
    private String parent;
    //模块地址
    private String moduleUrl;
    //菜单级别
    private String iLevel;
    //最下级
    private String leaf;
    //应用名称
    private String application;
    //控制器名称
    private String controller;
    //是否可用
    private Boolean enable;
    //优先级
    private String priority;

    //无参构造器
    public SysModulesEntity(){}

    //有参构造器
    public SysModulesEntity(Integer sysModulesId,String moduleName,String moduleDesc,String moduleType,String parent,String moduleUrl,String iLevel,String leaf,String application,String controller,Boolean enable,String priority){
        this.sysModulesId = sysModulesId;
        this.moduleName = moduleName;
        this.moduleDesc = moduleDesc;
        this.moduleType = moduleType;
        this.parent = parent;
        this.moduleUrl = moduleUrl;
        this.iLevel = iLevel;
        this.leaf = leaf;
        this.application = application;
        this.controller = controller;
        this.enable = enable;
        this.priority = priority;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "sys_modules_id" , nullable = false )
    public Integer getSysModulesId() {
        return sysModulesId;
    }

    public void setSysModulesId(int sysModulesId) {
        this.sysModulesId = sysModulesId;
    }

    @Basic
    @Column(name = "module_name" , nullable = false )
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Basic
    @Column(name = "module_desc" )
    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    @Basic
    @Column(name = "module_type" )
    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    @Basic
    @Column(name = "parent" )
    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Basic
    @Column(name = "module_url" )
    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    @Basic
    @Column(name = "i_level" )
    public String getILevel() {
        return iLevel;
    }

    public void setILevel(String iLevel) {
        this.iLevel = iLevel;
    }

    @Basic
    @Column(name = "leaf" )
    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    @Basic
    @Column(name = "application" )
    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Basic
    @Column(name = "controller" )
    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
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
    @Column(name = "priority" )
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SysModulesEntity)) return false;
        SysModulesEntity that = (SysModulesEntity) object;
        return Objects.equals(getSysModulesId(), that.getSysModulesId()) &&
            Objects.equals(getModuleName(), that.getModuleName()) &&
            Objects.equals(getModuleDesc(), that.getModuleDesc()) &&
            Objects.equals(getModuleType(), that.getModuleType()) &&
            Objects.equals(getParent(), that.getParent()) &&
            Objects.equals(getModuleUrl(), that.getModuleUrl()) &&
            Objects.equals(getILevel(), that.getILevel()) &&
            Objects.equals(getLeaf(), that.getLeaf()) &&
            Objects.equals(getApplication(), that.getApplication()) &&
            Objects.equals(getController(), that.getController()) &&
            Objects.equals(getEnable(), that.getEnable()) &&
            Objects.equals(getPriority(), that.getPriority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysModulesId(), getModuleName(), getModuleDesc(), getModuleType(), getParent(), getModuleUrl(), getILevel(), getLeaf(), getApplication(), getController(), getEnable(), getPriority());
    }

    @Override
    public String toString() {
        return "SysModulesEntity{" +
        "sysModulesId=" + sysModulesId +
        ",moduleName='" + moduleName + '\'' +
        ",moduleDesc='" + moduleDesc + '\'' +
        ",moduleType='" + moduleType + '\'' +
        ",parent='" + parent + '\'' +
        ",moduleUrl='" + moduleUrl + '\'' +
        ",iLevel='" + iLevel + '\'' +
        ",leaf='" + leaf + '\'' +
        ",application='" + application + '\'' +
        ",controller='" + controller + '\'' +
        ",enable=" + enable +
        ",priority='" + priority + '\'' +
        '}';
    }
}
