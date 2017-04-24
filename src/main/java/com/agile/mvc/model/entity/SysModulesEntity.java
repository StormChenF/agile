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
    private String pARENT;
    //模块地址
    private String moduleUrl;
    //菜单级别
    private String iLevel;
    //最下级
    private String lEAF;
    //应用名称
    private String aPPLICATION;
    //控制器名称
    private String cONTROLLER;
    //是否可用
    private Boolean eNABLE;
    //优先级
    private String pRIORITY;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_MODULES_ID" , nullable  = true)
    public Integer getSysModulesId() {
        return sysModulesId;
    }

    public void setsysModulesId(int sysModulesId) {
        this.sysModulesId = sysModulesId;
    }

    @Basic
    @Column(name = "MODULE_NAME" , nullable  = true)
    public String getModuleName() {
        return moduleName;
    }

    public void setmoduleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Basic
    @Column(name = "MODULE_DESC" , nullable  = false)
    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setmoduleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    @Basic
    @Column(name = "MODULE_TYPE" , nullable  = false)
    public String getModuleType() {
        return moduleType;
    }

    public void setmoduleType(String moduleType) {
        this.moduleType = moduleType;
    }

    @Basic
    @Column(name = "PARENT" , nullable  = false)
    public String getPARENT() {
        return pARENT;
    }

    public void setpARENT(String pARENT) {
        this.pARENT = pARENT;
    }

    @Basic
    @Column(name = "MODULE_URL" , nullable  = false)
    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setmoduleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    @Basic
    @Column(name = "I_LEVEL" , nullable  = false)
    public String getILevel() {
        return iLevel;
    }

    public void setiLevel(String iLevel) {
        this.iLevel = iLevel;
    }

    @Basic
    @Column(name = "LEAF" , nullable  = false)
    public String getLEAF() {
        return lEAF;
    }

    public void setlEAF(String lEAF) {
        this.lEAF = lEAF;
    }

    @Basic
    @Column(name = "APPLICATION" , nullable  = false)
    public String getAPPLICATION() {
        return aPPLICATION;
    }

    public void setaPPLICATION(String aPPLICATION) {
        this.aPPLICATION = aPPLICATION;
    }

    @Basic
    @Column(name = "CONTROLLER" , nullable  = false)
    public String getCONTROLLER() {
        return cONTROLLER;
    }

    public void setcONTROLLER(String cONTROLLER) {
        this.cONTROLLER = cONTROLLER;
    }

    @Basic
    @Column(name = "ENABLE" , nullable  = false)
    public Boolean getENABLE() {
        return eNABLE;
    }

    public void seteNABLE(Boolean eNABLE) {
        this.eNABLE = eNABLE;
    }

    @Basic
    @Column(name = "PRIORITY" , nullable  = false)
    public String getPRIORITY() {
        return pRIORITY;
    }

    public void setpRIORITY(String pRIORITY) {
        this.pRIORITY = pRIORITY;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysModulesEntity that = (SysModulesEntity) o;

        return Objects.equals(sysModulesId, that.sysModulesId) &&(moduleName != null ? moduleName.equals(that.moduleName) : that.moduleName == null) &&(moduleDesc != null ? moduleDesc.equals(that.moduleDesc) : that.moduleDesc == null) &&(moduleType != null ? moduleType.equals(that.moduleType) : that.moduleType == null) &&(pARENT != null ? pARENT.equals(that.pARENT) : that.pARENT == null) &&(moduleUrl != null ? moduleUrl.equals(that.moduleUrl) : that.moduleUrl == null) &&(iLevel != null ? iLevel.equals(that.iLevel) : that.iLevel == null) &&(lEAF != null ? lEAF.equals(that.lEAF) : that.lEAF == null) &&(aPPLICATION != null ? aPPLICATION.equals(that.aPPLICATION) : that.aPPLICATION == null) &&(cONTROLLER != null ? cONTROLLER.equals(that.cONTROLLER) : that.cONTROLLER == null) &&eNABLE == that.eNABLE &&(pRIORITY != null ? pRIORITY.equals(that.pRIORITY) : that.pRIORITY == null) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + sysModulesId;
        result = 31 * result + (moduleName != null ? moduleName.hashCode() : 0);
        result = 31 * result + (moduleDesc != null ? moduleDesc.hashCode() : 0);
        result = 31 * result + (moduleType != null ? moduleType.hashCode() : 0);
        result = 31 * result + (pARENT != null ? pARENT.hashCode() : 0);
        result = 31 * result + (moduleUrl != null ? moduleUrl.hashCode() : 0);
        result = 31 * result + (iLevel != null ? iLevel.hashCode() : 0);
        result = 31 * result + (lEAF != null ? lEAF.hashCode() : 0);
        result = 31 * result + (aPPLICATION != null ? aPPLICATION.hashCode() : 0);
        result = 31 * result + (cONTROLLER != null ? cONTROLLER.hashCode() : 0);
        result = 31 * result + (eNABLE ? 1 : 0);
        result = 31 * result + (pRIORITY != null ? pRIORITY.hashCode() : 0);
        return result;
    }
}
