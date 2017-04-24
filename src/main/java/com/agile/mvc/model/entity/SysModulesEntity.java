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
    private int sysModulesId;
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
    private boolean enable;
    //优先级
    private String priority;

    //无参构造器
    public SysModulesEntity(){}

    //有参构造器
    public SysModulesEntity(int sysModulesId, String moduleName, String moduleDesc, String moduleType, String parent, String moduleUrl, String iLevel, String leaf, String application, String controller, boolean enable, String priority ){
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
    @Column(name = "SYS_MODULES_ID" )
    public int getSysModulesId() {
        return sysModulesId;
    }

    public void setsysModulesId(int sysModulesId) {
        this.sysModulesId = sysModulesId;
    }

    @Basic
    @Column(name = "MODULE_NAME" )
    public String getModuleName() {
        return moduleName;
    }

    public void setmoduleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Basic
    @Column(name = "MODULE_DESC"  ,nullable = false )
    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setmoduleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    @Basic
    @Column(name = "MODULE_TYPE"  ,nullable = false )
    public String getModuleType() {
        return moduleType;
    }

    public void setmoduleType(String moduleType) {
        this.moduleType = moduleType;
    }

    @Basic
    @Column(name = "PARENT"  ,nullable = false )
    public String getPARENT() {
        return parent;
    }

    public void setpARENT(String parent) {
        this.parent = parent;
    }

    @Basic
    @Column(name = "MODULE_URL"  ,nullable = false )
    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setmoduleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    @Basic
    @Column(name = "I_LEVEL"  ,nullable = false )
    public String getILevel() {
        return iLevel;
    }

    public void setiLevel(String iLevel) {
        this.iLevel = iLevel;
    }

    @Basic
    @Column(name = "LEAF"  ,nullable = false )
    public String getLEAF() {
        return leaf;
    }

    public void setlEAF(String leaf) {
        this.leaf = leaf;
    }

    @Basic
    @Column(name = "APPLICATION"  ,nullable = false )
    public String getAPPLICATION() {
        return application;
    }

    public void setaPPLICATION(String application) {
        this.application = application;
    }

    @Basic
    @Column(name = "CONTROLLER"  ,nullable = false )
    public String getCONTROLLER() {
        return controller;
    }

    public void setcONTROLLER(String controller) {
        this.controller = controller;
    }

    @Basic
    @Column(name = "ENABLE"  ,nullable = false )
    public boolean getENABLE() {
        return enable;
    }

    public void seteNABLE(boolean enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "PRIORITY"  ,nullable = false )
    public String getPRIORITY() {
        return priority;
    }

    public void setpRIORITY(String priority) {
        this.priority = priority;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysModulesEntity that = (SysModulesEntity) o;

        return Objects.equals(sysModulesId, that.sysModulesId) &&(moduleName != null ? moduleName.equals(that.moduleName) : that.moduleName == null) &&(moduleDesc != null ? moduleDesc.equals(that.moduleDesc) : that.moduleDesc == null) &&(moduleType != null ? moduleType.equals(that.moduleType) : that.moduleType == null) &&(parent != null ? parent.equals(that.parent) : that.parent == null) &&(moduleUrl != null ? moduleUrl.equals(that.moduleUrl) : that.moduleUrl == null) &&(iLevel != null ? iLevel.equals(that.iLevel) : that.iLevel == null) &&(leaf != null ? leaf.equals(that.leaf) : that.leaf == null) &&(application != null ? application.equals(that.application) : that.application == null) &&(controller != null ? controller.equals(that.controller) : that.controller == null) &&enable == that.enable &&(priority != null ? priority.equals(that.priority) : that.priority == null) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + sysModulesId;
        result = 31 * result + (moduleName != null ? moduleName.hashCode() : 0);
        result = 31 * result + (moduleDesc != null ? moduleDesc.hashCode() : 0);
        result = 31 * result + (moduleType != null ? moduleType.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (moduleUrl != null ? moduleUrl.hashCode() : 0);
        result = 31 * result + (iLevel != null ? iLevel.hashCode() : 0);
        result = 31 * result + (leaf != null ? leaf.hashCode() : 0);
        result = 31 * result + (application != null ? application.hashCode() : 0);
        result = 31 * result + (controller != null ? controller.hashCode() : 0);
        result = 31 * result + (enable ? 1 : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        return result;
    }
}
