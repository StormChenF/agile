package com.agile.mvc.service;

import com.agile.common.annotation.*;
import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.LogValueEntity;

/**
 * Created by 佟盟
 */
@Service
public class LogValueService extends MainService {

    @API(name="保存",
        tag = {
            @Tag(name = "LogValue",description = "LogValue")
        },
        method = API.Method.POST,
        summary = "新增日志相关字段值变动信息",
        description = "新增日志相关字段值变动信息",
        parameters = {

            @Param(name = "logTableId",in = "添加",description = "日志相关表标识",type = Param.Type.STRING),
            @Param(name = "columnName",in = "添加",description = "字段",type = Param.Type.STRING),
            @Param(name = "columnType",in = "添加",description = "字段类型",type = Param.Type.STRING),
            @Param(name = "newValue",in = "添加",description = "新值",required = true,type = Param.Type.STRING),
            @Param(name = "oldValue",in = "添加",description = "旧值",required = true,type = Param.Type.STRING),
            @Param(name = "columnInfo",in = "添加",description = "字段含义",required = true,type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN save() throws IllegalAccessException {
        LogValueEntity entity = ObjectUtil.getObjectFromMap(LogValueEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    @API(name="删除",
        tag = {
            @Tag(name = "LogValue",description = "LogValue")
        },
        method = API.Method.POST,
        summary = "删除日志相关字段值变动信息",
        description = "删除日志相关字段值变动信息",
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
            dao.deleteInBatch(LogValueEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    @API(name="更新",
        tag = {
            @Tag(name = "LogValue",description = "LogValue")
        },
        method = API.Method.POST,
        summary = "更新日志相关字段值变动信息",
        description = "更新日志相关字段值变动信息",
        parameters = {
            @Param(name = "logValueId",in = "更新",description = "唯一标识",required = true,type = Param.Type.STRING),
            @Param(name = "logTableId",in = "更新",description = "日志相关表标识",type = Param.Type.STRING),
            @Param(name = "columnName",in = "更新",description = "字段",type = Param.Type.STRING),
            @Param(name = "columnType",in = "更新",description = "字段类型",type = Param.Type.STRING),
            @Param(name = "newValue",in = "更新",description = "新值",type = Param.Type.STRING),
            @Param(name = "oldValue",in = "更新",description = "旧值",type = Param.Type.STRING),
            @Param(name = "columnInfo",in = "更新",description = "字段含义",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN update() throws IllegalAccessException {
        LogValueEntity entity = ObjectUtil.getObjectFromMap(LogValueEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getLogValueId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    @API(name="查询",
        tag = {
            @Tag(name = "LogValue",description = "LogValue")
        },
        method = API.Method.GET,
        summary = "查询日志相关字段值变动信息",
        description = "查询日志相关字段值变动信息",
        parameters = {
            @Param(name = "page",in = "查询",description = "第几页",required = false,type = Param.Type.INTEGER),
            @Param(name = "size",in = "查询",description = "每页条数",required = false,type = Param.Type.INTEGER)
        },
        responses = {
        @Responses(code = "000001",description = "成功",schema = LogValueEntity.class,isArray = true),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(LogValueEntity.class,getInParamOfInteger("page",0),getInParamOfInteger("size",10)));
        return RETURN.SUCCESS;
    }
}
