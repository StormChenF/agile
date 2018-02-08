package com.agile.mvc.service;

import com.agile.common.annotation.*;
import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.LogMainEntity;

/**
 * Created by 佟盟
 */
@Service
public class LogMainService extends MainService {

    @API(name="保存",
        tag = {
            @Tag(name = "LogMain",description = "LogMain")
        },
        method = API.Method.POST,
        summary = "新增日志表",
        description = "新增日志表",
        parameters = {

            @Param(name = "businessCode",in = "添加",description = "业务编码",type = Param.Type.STRING),
            @Param(name = "targetType",in = "添加",description = "业务对象类型",type = Param.Type.STRING),
            @Param(name = "targetCode",in = "添加",description = "业务对象标识",type = Param.Type.STRING),
            @Param(name = "userId",in = "添加",description = "操作人",type = Param.Type.STRING),
            @Param(name = "createTime",in = "添加",description = "操作时间",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN save() throws IllegalAccessException {
        LogMainEntity entity = ObjectUtil.getObjectFromMap(LogMainEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    @API(name="删除",
        tag = {
            @Tag(name = "LogMain",description = "LogMain")
        },
        method = API.Method.POST,
        summary = "删除日志表",
        description = "删除日志表",
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
            dao.deleteInBatch(LogMainEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    @API(name="更新",
        tag = {
            @Tag(name = "LogMain",description = "LogMain")
        },
        method = API.Method.POST,
        summary = "更新日志表",
        description = "更新日志表",
        parameters = {
            @Param(name = "logMainId",in = "更新",description = "唯一标识",required = true,type = Param.Type.STRING),
            @Param(name = "businessCode",in = "更新",description = "业务编码",type = Param.Type.STRING),
            @Param(name = "targetType",in = "更新",description = "业务对象类型",type = Param.Type.STRING),
            @Param(name = "targetCode",in = "更新",description = "业务对象标识",type = Param.Type.STRING),
            @Param(name = "userId",in = "更新",description = "操作人",type = Param.Type.STRING),
            @Param(name = "createTime",in = "更新",description = "操作时间",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN update() throws IllegalAccessException {
        LogMainEntity entity = ObjectUtil.getObjectFromMap(LogMainEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getLogMainId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    @API(name="查询",
        tag = {
            @Tag(name = "LogMain",description = "LogMain")
        },
        method = API.Method.GET,
        summary = "查询日志表",
        description = "查询日志表",
        parameters = {
            @Param(name = "page",in = "查询",description = "第几页",required = false,type = Param.Type.INTEGER),
            @Param(name = "size",in = "查询",description = "每页条数",required = false,type = Param.Type.INTEGER)
        },
        responses = {
        @Responses(code = "000001",description = "成功",schema = LogMainEntity.class,isArray = true),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(LogMainEntity.class,getInParamOfInteger("page",0),getInParamOfInteger("size",10)));
        return RETURN.SUCCESS;
    }
}
