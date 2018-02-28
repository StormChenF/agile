package com.agile.mvc.service;

import com.agile.common.annotation.*;
import com.agile.common.service.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.SysModulesEntity;

/**
 * Created by 佟盟
 */
@Service
public class SysModulesService extends MainService {

    @API(name="保存",
        tag = {
            @Tag(name = "[系统管理]模块",description = "SysModules")
        },
        method = API.Method.POST,
        summary = "新增[系统管理]模块",
        description = "新增[系统管理]模块",
        parameters = {

            @Param(name = "moduleName",in = "添加",description = "模块名称",type = Param.Type.STRING),
            @Param(name = "moduleDesc",in = "添加",description = "模块说明",required = true,type = Param.Type.STRING),
            @Param(name = "moduleType",in = "添加",description = "模块类型",required = true,type = Param.Type.STRING),
            @Param(name = "parent",in = "添加",description = "模块上级",required = true,type = Param.Type.STRING),
            @Param(name = "moduleUrl",in = "添加",description = "模块地址",required = true,type = Param.Type.STRING),
            @Param(name = "iLevel",in = "添加",description = "菜单级别",required = true,type = Param.Type.STRING),
            @Param(name = "leaf",in = "添加",description = "最下级",required = true,type = Param.Type.STRING),
            @Param(name = "application",in = "添加",description = "应用名称",required = true,type = Param.Type.STRING),
            @Param(name = "controller",in = "添加",description = "控制器名称",required = true,type = Param.Type.STRING),
            @Param(name = "enable",in = "添加",description = "是否可用",required = true,type = Param.Type.STRING),
            @Param(name = "priority",in = "添加",description = "优先级",required = true,type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN save() {
        SysModulesEntity entity = ObjectUtil.getObjectFromMap(SysModulesEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    @API(name="删除",
        tag = {
            @Tag(name = "[系统管理]模块",description = "SysModules")
        },
        method = API.Method.POST,
        summary = "删除[系统管理]模块",
        description = "删除[系统管理]模块",
        parameters = {
                @Param(name = "ids",in = "删除",description = "主键字符串",required = true,type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN delete(){
        if (this.containsKey("id")){
            String[] ids = this.getInParamOfArray("id");
            dao.deleteInBatch(SysModulesEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    @API(name="更新",
        tag = {
            @Tag(name = "[系统管理]模块",description = "SysModules")
        },
        method = API.Method.POST,
        summary = "更新[系统管理]模块",
        description = "更新[系统管理]模块",
        parameters = {
            @Param(name = "sysModulesId",in = "更新",description = "唯一标识",required = true,type = Param.Type.STRING),
            @Param(name = "moduleName",in = "更新",description = "模块名称",type = Param.Type.STRING),
            @Param(name = "moduleDesc",in = "更新",description = "模块说明",type = Param.Type.STRING),
            @Param(name = "moduleType",in = "更新",description = "模块类型",type = Param.Type.STRING),
            @Param(name = "parent",in = "更新",description = "模块上级",type = Param.Type.STRING),
            @Param(name = "moduleUrl",in = "更新",description = "模块地址",type = Param.Type.STRING),
            @Param(name = "iLevel",in = "更新",description = "菜单级别",type = Param.Type.STRING),
            @Param(name = "leaf",in = "更新",description = "最下级",type = Param.Type.STRING),
            @Param(name = "application",in = "更新",description = "应用名称",type = Param.Type.STRING),
            @Param(name = "controller",in = "更新",description = "控制器名称",type = Param.Type.STRING),
            @Param(name = "enable",in = "更新",description = "是否可用",type = Param.Type.STRING),
            @Param(name = "priority",in = "更新",description = "优先级",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN update() {
        SysModulesEntity entity = ObjectUtil.getObjectFromMap(SysModulesEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysModulesId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    @API(name="查询",
        tag = {
            @Tag(name = "[系统管理]模块",description = "SysModules")
        },
        method = API.Method.GET,
        summary = "查询[系统管理]模块",
        description = "查询[系统管理]模块",
        parameters = {
            @Param(name = "page",in = "查询",description = "第几页",type = Param.Type.INTEGER),
            @Param(name = "size",in = "查询",description = "每页条数",type = Param.Type.INTEGER)
        },
        responses = {
        @Responses(code = "000001",description = "成功",schema = SysModulesEntity.class,isArray = true),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(SysModulesEntity.class,getInParam("page",Integer.class,0),getInParam("size",Integer.class,10)));
        return RETURN.SUCCESS;
    }
}
