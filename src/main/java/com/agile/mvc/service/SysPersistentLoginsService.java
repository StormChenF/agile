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
            @Tag(name = "[系统管理]登陆用户信息",description = "SysPersistentLogins")
        },
        method = API.Method.POST,
        summary = "新增[系统管理]登陆用户信息",
        description = "新增[系统管理]登陆用户信息",
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
    public RETURN save() {
        SysPersistentLoginsEntity entity = ObjectUtil.getObjectFromMap(SysPersistentLoginsEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    @API(name="删除",
        tag = {
            @Tag(name = "[系统管理]登陆用户信息",description = "SysPersistentLogins")
        },
        method = API.Method.POST,
        summary = "删除[系统管理]登陆用户信息",
        description = "删除[系统管理]登陆用户信息",
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
            dao.deleteInBatch(SysPersistentLoginsEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    @API(name="更新",
        tag = {
            @Tag(name = "[系统管理]登陆用户信息",description = "SysPersistentLogins")
        },
        method = API.Method.POST,
        summary = "更新[系统管理]登陆用户信息",
        description = "更新[系统管理]登陆用户信息",
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
    public RETURN update() {
        SysPersistentLoginsEntity entity = ObjectUtil.getObjectFromMap(SysPersistentLoginsEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysPersistentLoginsId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    @API(name="查询",
        tag = {
            @Tag(name = "[系统管理]登陆用户信息",description = "SysPersistentLogins")
        },
        method = API.Method.GET,
        summary = "查询[系统管理]登陆用户信息",
        description = "查询[系统管理]登陆用户信息",
        parameters = {
            @Param(name = "page",in = "查询",description = "第几页",type = Param.Type.INTEGER),
            @Param(name = "size",in = "查询",description = "每页条数",type = Param.Type.INTEGER)
        },
        responses = {
        @Responses(code = "000001",description = "成功",schema = SysPersistentLoginsEntity.class,isArray = true),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(SysPersistentLoginsEntity.class,getInParam("page",Integer.class,0),getInParam("size",Integer.class,10)));
        return RETURN.SUCCESS;
    }
}
