package com.agile.mvc.service;

import com.agile.common.annotation.*;
import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.SysTaskEntity;

/**
 * Created by 佟盟
 */
@Service
public class SysTaskService extends MainService {

    @API(name="保存",
        tag = {
            @Tag(name = "SysTask",description = "SysTask")
        },
        method = API.Method.POST,
        summary = "新增SysTaskEntity",
        description = "新增SysTaskEntity",
        parameters = {

            @Param(name = "name",in = "添加",description = "定时任务名",required = true,type = Param.Type.STRING),
            @Param(name = "state",in = "添加",description = "状态",required = true,type = Param.Type.STRING),
            @Param(name = "cron",in = "添加",description = "定时表达式",required = true,type = Param.Type.STRING),
            @Param(name = "sync",in = "添加",description = "是否同步",required = true,type = Param.Type.STRING),
            @Param(name = "updateTime",in = "添加",description = "更新时间",required = true,type = Param.Type.STRING),
            @Param(name = "createTime",in = "添加",description = "创建时间",required = true,type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN save() throws IllegalAccessException {
        SysTaskEntity entity = ObjectUtil.getObjectFromMap(SysTaskEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    @API(name="删除",
        tag = {
            @Tag(name = "SysTask",description = "SysTask")
        },
        method = API.Method.POST,
        summary = "删除SysTaskEntity",
        description = "删除SysTaskEntity",
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
            dao.deleteInBatch(SysTaskEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    @API(name="更新",
        tag = {
            @Tag(name = "SysTask",description = "SysTask")
        },
        method = API.Method.POST,
        summary = "更新SysTaskEntity",
        description = "更新SysTaskEntity",
        parameters = {
            @Param(name = "sysTaskId",in = "更新",description = "主键",required = true,type = Param.Type.STRING),
            @Param(name = "name",in = "更新",description = "定时任务名",type = Param.Type.STRING),
            @Param(name = "state",in = "更新",description = "状态",type = Param.Type.STRING),
            @Param(name = "cron",in = "更新",description = "定时表达式",type = Param.Type.STRING),
            @Param(name = "sync",in = "更新",description = "是否同步",type = Param.Type.STRING),
            @Param(name = "updateTime",in = "更新",description = "更新时间",type = Param.Type.STRING),
            @Param(name = "createTime",in = "更新",description = "创建时间",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN update() throws IllegalAccessException {
        SysTaskEntity entity = ObjectUtil.getObjectFromMap(SysTaskEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysTaskId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    @API(name="查询",
        tag = {
            @Tag(name = "SysTask",description = "SysTask")
        },
        method = API.Method.GET,
        summary = "查询SysTaskEntity",
        description = "查询SysTaskEntity",
        parameters = {
            @Param(name = "page",in = "查询",description = "第几页",required = false,type = Param.Type.INTEGER),
            @Param(name = "size",in = "查询",description = "每页条数",required = false,type = Param.Type.INTEGER)
        },
        responses = {
        @Responses(code = "000001",description = "成功",schema = SysTaskEntity.class,isArray = true),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(SysTaskEntity.class,getInParamOfInteger("page",0),getInParamOfInteger("size",10)));
        return RETURN.SUCCESS;
    }
}
