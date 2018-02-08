package com.agile.mvc.service;

import com.agile.common.annotation.*;
import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.SysPersistentLoginsEntity;

/**
 * Created by 佟盟
 */
@Service
public class SysPersistentLoginsService extends MainService {

    @API(name="保存",
        tag = {
            @Tag(name = "SysPersistentLogins",description = "SysPersistentLogins")
        },
        method = API.Method.POST,
        summary = "新增Spring Remember me 持久化",
        description = "新增Spring Remember me 持久化",
        parameters = {

            @Param(name = "username",in = "添加",description = "用户名",required = true,type = Param.Type.STRING),
            @Param(name = "series",in = "添加",description = "序列",type = Param.Type.STRING),
            @Param(name = "token",in = "添加",description = "认证信息",required = true,type = Param.Type.STRING),
            @Param(name = "lastUsed",in = "添加",description = "最后时间",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN save() throws IllegalAccessException {
        SysPersistentLoginsEntity entity = ObjectUtil.getObjectFromMap(SysPersistentLoginsEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    @API(name="删除",
        tag = {
            @Tag(name = "SysPersistentLogins",description = "SysPersistentLogins")
        },
        method = API.Method.POST,
        summary = "删除Spring Remember me 持久化",
        description = "删除Spring Remember me 持久化",
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
            dao.deleteInBatch(SysPersistentLoginsEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    @API(name="更新",
        tag = {
            @Tag(name = "SysPersistentLogins",description = "SysPersistentLogins")
        },
        method = API.Method.POST,
        summary = "更新Spring Remember me 持久化",
        description = "更新Spring Remember me 持久化",
        parameters = {
            @Param(name = "sysPersistentLoginsId",in = "更新",description = "唯一标识",required = true,type = Param.Type.STRING),
            @Param(name = "username",in = "更新",description = "用户名",type = Param.Type.STRING),
            @Param(name = "series",in = "更新",description = "序列",type = Param.Type.STRING),
            @Param(name = "token",in = "更新",description = "认证信息",type = Param.Type.STRING),
            @Param(name = "lastUsed",in = "更新",description = "最后时间",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN update() throws IllegalAccessException {
        SysPersistentLoginsEntity entity = ObjectUtil.getObjectFromMap(SysPersistentLoginsEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysPersistentLoginsId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    @API(name="查询",
        tag = {
            @Tag(name = "SysPersistentLogins",description = "SysPersistentLogins")
        },
        method = API.Method.GET,
        summary = "查询Spring Remember me 持久化",
        description = "查询Spring Remember me 持久化",
        parameters = {
            @Param(name = "page",in = "查询",description = "第几页",required = false,type = Param.Type.INTEGER),
            @Param(name = "size",in = "查询",description = "每页条数",required = false,type = Param.Type.INTEGER)
        },
        responses = {
        @Responses(code = "000001",description = "成功",schema = SysPersistentLoginsEntity.class,isArray = true),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(SysPersistentLoginsEntity.class,getInParamOfInteger("page",0),getInParamOfInteger("size",10)));
        return RETURN.SUCCESS;
    }
}
