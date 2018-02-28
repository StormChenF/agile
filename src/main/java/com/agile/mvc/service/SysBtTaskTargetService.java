package com.agile.mvc.service;

import com.agile.common.annotation.*;
import com.agile.common.service.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.SysBtTaskTargetEntity;

/**
 * Created by 佟盟
 */
@Service
public class SysBtTaskTargetService extends MainService {

    @API(name="保存",
        tag = {
            @Tag(name = "[系统管理]定时任务目标任务表",description = "SysBtTaskTarget")
        },
        method = API.Method.POST,
        summary = "新增[系统管理]定时任务目标任务表",
        description = "新增[系统管理]定时任务目标任务表",
        parameters = {

            @Param(name = "sysTaskId",in = "添加",description = "定时任务标志",type = Param.Type.STRING),
            @Param(name = "sysTaskTargetId",in = "添加",description = "目标方法主键",type = Param.Type.STRING),
            @Param(name = "order",in = "添加",description = "优先级",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN save() {
        SysBtTaskTargetEntity entity = ObjectUtil.getObjectFromMap(SysBtTaskTargetEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    @API(name="删除",
        tag = {
            @Tag(name = "[系统管理]定时任务目标任务表",description = "SysBtTaskTarget")
        },
        method = API.Method.POST,
        summary = "删除[系统管理]定时任务目标任务表",
        description = "删除[系统管理]定时任务目标任务表",
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
            dao.deleteInBatch(SysBtTaskTargetEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    @API(name="更新",
        tag = {
            @Tag(name = "[系统管理]定时任务目标任务表",description = "SysBtTaskTarget")
        },
        method = API.Method.POST,
        summary = "更新[系统管理]定时任务目标任务表",
        description = "更新[系统管理]定时任务目标任务表",
        parameters = {
            @Param(name = "sysBtTaskTargetId",in = "更新",description = "主键",required = true,type = Param.Type.STRING),
            @Param(name = "sysTaskId",in = "更新",description = "定时任务标志",type = Param.Type.STRING),
            @Param(name = "sysTaskTargetId",in = "更新",description = "目标方法主键",type = Param.Type.STRING),
            @Param(name = "order",in = "更新",description = "优先级",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN update() {
        SysBtTaskTargetEntity entity = ObjectUtil.getObjectFromMap(SysBtTaskTargetEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysBtTaskTargetId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    @API(name="查询",
        tag = {
            @Tag(name = "[系统管理]定时任务目标任务表",description = "SysBtTaskTarget")
        },
        method = API.Method.GET,
        summary = "查询[系统管理]定时任务目标任务表",
        description = "查询[系统管理]定时任务目标任务表",
        parameters = {
            @Param(name = "page",in = "查询",description = "第几页",type = Param.Type.INTEGER),
            @Param(name = "size",in = "查询",description = "每页条数",type = Param.Type.INTEGER)
        },
        responses = {
        @Responses(code = "000001",description = "成功",schema = SysBtTaskTargetEntity.class,isArray = true),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(SysBtTaskTargetEntity.class,getInParam("page",Integer.class,0),getInParam("size",Integer.class,10)));
        return RETURN.SUCCESS;
    }
}
