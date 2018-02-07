package com.agile.mvc.service;

import com.agile.common.annotation.*;
import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.SysTaskTargetEntity;

/**
 * Created by 佟盟
 */
@Service
public class SysTaskTargetService extends MainService {

    @API(name="保存",
        tag = {
            @Tag(name = "SysTaskTarget",description = "SysTaskTarget")
        },
        method = API.Method.POST,
        summary = "新增SysTaskTargetEntity",
        description = "新增SysTaskTargetEntity",
        parameters = {

            @Param(name = "name",in = "添加",description = "方法含义名",required = true,type = Param.Type.STRING),
            @Param(name = "targetPackage",in = "添加",description = "包名",type = Param.Type.STRING),
            @Param(name = "targetClass",in = "添加",description = "类名",type = Param.Type.STRING),
            @Param(name = "targetMethod",in = "添加",description = "方法名",type = Param.Type.STRING),
            @Param(name = "remarks",in = "添加",description = "备注",required = true,type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN save() throws IllegalAccessException {
        SysTaskTargetEntity entity = ObjectUtil.getObjectFromMap(SysTaskTargetEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    @API(name="删除",
        tag = {
            @Tag(name = "SysTaskTarget",description = "SysTaskTarget")
        },
        method = API.Method.POST,
        summary = "删除SysTaskTargetEntity",
        description = "删除SysTaskTargetEntity",
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
            dao.deleteInBatch(SysTaskTargetEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    @API(name="更新",
        tag = {
            @Tag(name = "SysTaskTarget",description = "SysTaskTarget")
        },
        method = API.Method.POST,
        summary = "更新SysTaskTargetEntity",
        description = "更新SysTaskTargetEntity",
        parameters = {
            @Param(name = "sysTaskTargetId",in = "更新",description = "唯一标识",required = true,type = Param.Type.STRING),
            @Param(name = "name",in = "更新",description = "方法含义名",type = Param.Type.STRING),
            @Param(name = "targetPackage",in = "更新",description = "包名",type = Param.Type.STRING),
            @Param(name = "targetClass",in = "更新",description = "类名",type = Param.Type.STRING),
            @Param(name = "targetMethod",in = "更新",description = "方法名",type = Param.Type.STRING),
            @Param(name = "remarks",in = "更新",description = "备注",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN update() throws IllegalAccessException {
        SysTaskTargetEntity entity = ObjectUtil.getObjectFromMap(SysTaskTargetEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysTaskTargetId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    @API(name="查询",
        tag = {
            @Tag(name = "SysTaskTarget",description = "SysTaskTarget")
        },
        method = API.Method.GET,
        summary = "查询SysTaskTargetEntity",
        description = "查询SysTaskTargetEntity",
        parameters = {
            @Param(name = "page",in = "查询",description = "第几页",required = false,type = Param.Type.INTEGER),
            @Param(name = "size",in = "查询",description = "每页条数",required = false,type = Param.Type.INTEGER)
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(SysTaskTargetEntity.class,getInParamOfInteger("page",0),getInParamOfInteger("size",10)));
        return RETURN.SUCCESS;
    }
}
