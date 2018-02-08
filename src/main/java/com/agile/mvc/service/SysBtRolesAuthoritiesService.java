package com.agile.mvc.service;

import com.agile.common.annotation.*;
import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.SysBtRolesAuthoritiesEntity;

/**
 * Created by 佟盟
 */
@Service
public class SysBtRolesAuthoritiesService extends MainService {

    @API(name="保存",
        tag = {
            @Tag(name = "SysBtRolesAuthorities",description = "SysBtRolesAuthorities")
        },
        method = API.Method.POST,
        summary = "新增[系统管理]角色权限表",
        description = "新增[系统管理]角色权限表",
        parameters = {

            @Param(name = "authorityId",in = "添加",description = "权限唯一标识",type = Param.Type.STRING),
            @Param(name = "roleId",in = "添加",description = "角色唯一标识",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN save() throws IllegalAccessException {
        SysBtRolesAuthoritiesEntity entity = ObjectUtil.getObjectFromMap(SysBtRolesAuthoritiesEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    @API(name="删除",
        tag = {
            @Tag(name = "SysBtRolesAuthorities",description = "SysBtRolesAuthorities")
        },
        method = API.Method.POST,
        summary = "删除[系统管理]角色权限表",
        description = "删除[系统管理]角色权限表",
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
            dao.deleteInBatch(SysBtRolesAuthoritiesEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    @API(name="更新",
        tag = {
            @Tag(name = "SysBtRolesAuthorities",description = "SysBtRolesAuthorities")
        },
        method = API.Method.POST,
        summary = "更新[系统管理]角色权限表",
        description = "更新[系统管理]角色权限表",
        parameters = {
            @Param(name = "sysBtRolesAuthoritiesId",in = "更新",description = "唯一标识",required = true,type = Param.Type.STRING),
            @Param(name = "authorityId",in = "更新",description = "权限唯一标识",type = Param.Type.STRING),
            @Param(name = "roleId",in = "更新",description = "角色唯一标识",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN update() throws IllegalAccessException {
        SysBtRolesAuthoritiesEntity entity = ObjectUtil.getObjectFromMap(SysBtRolesAuthoritiesEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysBtRolesAuthoritiesId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    @API(name="查询",
        tag = {
            @Tag(name = "SysBtRolesAuthorities",description = "SysBtRolesAuthorities")
        },
        method = API.Method.GET,
        summary = "查询[系统管理]角色权限表",
        description = "查询[系统管理]角色权限表",
        parameters = {
            @Param(name = "page",in = "查询",description = "第几页",required = false,type = Param.Type.INTEGER),
            @Param(name = "size",in = "查询",description = "每页条数",required = false,type = Param.Type.INTEGER)
        },
        responses = {
        @Responses(code = "000001",description = "成功",schema = SysBtRolesAuthoritiesEntity.class,isArray = true),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(SysBtRolesAuthoritiesEntity.class,getInParamOfInteger("page",0),getInParamOfInteger("size",10)));
        return RETURN.SUCCESS;
    }
}
