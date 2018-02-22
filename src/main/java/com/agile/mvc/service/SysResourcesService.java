package com.agile.mvc.service;

import com.agile.common.annotation.*;
import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.SysResourcesEntity;

/**
 * Created by 佟盟
 */
@Service
public class SysResourcesService extends MainService {

    @API(name="保存",
        tag = {
            @Tag(name = "[系统管理]资源",description = "SysResources")
        },
        method = API.Method.POST,
        summary = "新增[系统管理]资源",
        description = "新增[系统管理]资源",
        parameters = {

            @Param(name = "resourceType",in = "添加",description = "资源类型",required = true,type = Param.Type.STRING),
            @Param(name = "resourceName",in = "添加",description = "资源名称",required = true,type = Param.Type.STRING),
            @Param(name = "resourceDesc",in = "添加",description = "资源描述",required = true,type = Param.Type.STRING),
            @Param(name = "resourcePath",in = "添加",description = "资源路径",required = true,type = Param.Type.STRING),
            @Param(name = "priority",in = "添加",description = "优先级",required = true,type = Param.Type.STRING),
            @Param(name = "enable",in = "添加",description = "是否可用",required = true,type = Param.Type.STRING),
            @Param(name = "issys",in = "添加",description = "是否系统权限",required = true,type = Param.Type.STRING),
            @Param(name = "moduleId",in = "添加",description = "模块",required = true,type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN save() throws IllegalAccessException {
        SysResourcesEntity entity = ObjectUtil.getObjectFromMap(SysResourcesEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    @API(name="删除",
        tag = {
            @Tag(name = "[系统管理]资源",description = "SysResources")
        },
        method = API.Method.POST,
        summary = "删除[系统管理]资源",
        description = "删除[系统管理]资源",
        parameters = {
                @Param(name = "ids",in = "删除",description = "主键字符串",required = true,type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN delete(){
        if (this.containsKey("ids")){
            String[] ids = this.getInParamOfString("ids").split(",");
            dao.deleteInBatch(SysResourcesEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    @API(name="更新",
        tag = {
            @Tag(name = "[系统管理]资源",description = "SysResources")
        },
        method = API.Method.POST,
        summary = "更新[系统管理]资源",
        description = "更新[系统管理]资源",
        parameters = {
            @Param(name = "sysResourcesId",in = "更新",description = "唯一标识",required = true,type = Param.Type.STRING),
            @Param(name = "resourceType",in = "更新",description = "资源类型",type = Param.Type.STRING),
            @Param(name = "resourceName",in = "更新",description = "资源名称",type = Param.Type.STRING),
            @Param(name = "resourceDesc",in = "更新",description = "资源描述",type = Param.Type.STRING),
            @Param(name = "resourcePath",in = "更新",description = "资源路径",type = Param.Type.STRING),
            @Param(name = "priority",in = "更新",description = "优先级",type = Param.Type.STRING),
            @Param(name = "enable",in = "更新",description = "是否可用",type = Param.Type.STRING),
            @Param(name = "issys",in = "更新",description = "是否系统权限",type = Param.Type.STRING),
            @Param(name = "moduleId",in = "更新",description = "模块",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN update() throws IllegalAccessException {
        SysResourcesEntity entity = ObjectUtil.getObjectFromMap(SysResourcesEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysResourcesId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    @API(name="查询",
        tag = {
            @Tag(name = "[系统管理]资源",description = "SysResources")
        },
        method = API.Method.GET,
        summary = "查询[系统管理]资源",
        description = "查询[系统管理]资源",
        parameters = {
            @Param(name = "page",in = "查询",description = "第几页",required = false,type = Param.Type.INTEGER),
            @Param(name = "size",in = "查询",description = "每页条数",required = false,type = Param.Type.INTEGER)
        },
        responses = {
        @Responses(code = "000001",description = "成功",schema = SysResourcesEntity.class,isArray = true),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(SysResourcesEntity.class,getInParamOfInteger("page",0),getInParamOfInteger("size",10)));
        return RETURN.SUCCESS;
    }
}
