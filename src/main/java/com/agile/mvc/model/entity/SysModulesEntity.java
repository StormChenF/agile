package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/3/20.
 */
@Entity
@Table(name = "sys_modules", schema = "agile_db", catalog = "")
public class SysModulesEntity {
    private int moduleId;
    private String moduleName;
    private String moduleDesc;
    private String moduleType;
    private String parent;
    private String moduleUrl;
    private String iLevel;
    private String leaf;
    private String application;
    private String controller;
    private Boolean enable;
    private String priority;
    private int sysModulesId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_MODULES_ID")
    public int getSysModulesId() {
        return sysModulesId;
    }

    public void setSysModulesId(int sysModulesId) {
        this.sysModulesId = sysModulesId;
    }

    @Basic
    @Column(name = "MODULE_NAME", nullable = false, length = 100)
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Basic
    @Column(name = "MODULE_DESC", nullable = true, length = 200)
    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    @Basic
    @Column(name = "MODULE_TYPE", nullable = true, length = 100)
    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    @Basic
    @Column(name = "PARENT", nullable = true, length = 100)
    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Basic
    @Column(name = "MODULE_URL", nullable = true, length = 100)
    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    @Basic
    @Column(name = "I_LEVEL", nullable = true, length = 4)
    public String getiLevel() {
        return iLevel;
    }

    public void setiLevel(String iLevel) {
        this.iLevel = iLevel;
    }

    @Basic
    @Column(name = "LEAF", nullable = true, length = 4)
    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    @Basic
    @Column(name = "APPLICATION", nullable = true, length = 100)
    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Basic
    @Column(name = "CONTROLLER", nullable = true, length = 100)
    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
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
    @Column(name = "PRIORITY", nullable = true, length = 4)
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysModulesEntity that = (SysModulesEntity) o;

        if (moduleId != that.moduleId) return false;
        if (moduleName != null ? !moduleName.equals(that.moduleName) : that.moduleName != null) return false;
        if (moduleDesc != null ? !moduleDesc.equals(that.moduleDesc) : that.moduleDesc != null) return false;
        if (moduleType != null ? !moduleType.equals(that.moduleType) : that.moduleType != null) return false;
        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
        if (moduleUrl != null ? !moduleUrl.equals(that.moduleUrl) : that.moduleUrl != null) return false;
        if (iLevel != null ? !iLevel.equals(that.iLevel) : that.iLevel != null) return false;
        if (leaf != null ? !leaf.equals(that.leaf) : that.leaf != null) return false;
        if (application != null ? !application.equals(that.application) : that.application != null) return false;
        if (controller != null ? !controller.equals(that.controller) : that.controller != null) return false;
        if (enable != null ? !enable.equals(that.enable) : that.enable != null) return false;
        if (priority != null ? !priority.equals(that.priority) : that.priority != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = moduleId;
        result = 31 * result + (moduleName != null ? moduleName.hashCode() : 0);
        result = 31 * result + (moduleDesc != null ? moduleDesc.hashCode() : 0);
        result = 31 * result + (moduleType != null ? moduleType.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (moduleUrl != null ? moduleUrl.hashCode() : 0);
        result = 31 * result + (iLevel != null ? iLevel.hashCode() : 0);
        result = 31 * result + (leaf != null ? leaf.hashCode() : 0);
        result = 31 * result + (application != null ? application.hashCode() : 0);
        result = 31 * result + (controller != null ? controller.hashCode() : 0);
        result = 31 * result + (enable != null ? enable.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        return result;
    }
}
