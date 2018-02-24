package com.agile.mvc.service;

import com.agile.common.annotation.*;
import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.SysRolesEntity;

/**
 * Created by 佟盟
 */
@Service
public class SysRolesService extends MainService {

    @API(name="保存",
        tag = {
            @Tag(name = "[系统管理]角色",description = "SysRoles")
        },
        method = API.Method.POST,
        summary = "新增[系统管理]角色",
        description = "新增[系统管理]角色",
        parameters = {

            @Param(name = "roleName",in = "添加",description = "角色名称",required = true,type = Param.Type.STRING),
            @Param(name = "roleDesc",in = "添加",description = "角色说明",required = true,type = Param.Type.STRING),
            @Param(name = "enable",in = "添加",description = "是否可用",required = true,type = Param.Type.STRING),
            @Param(name = "issys",in = "添加",description = "是否系统权限",required = true,type = Param.Type.STRING),
            @Param(name = "moduleId",in = "添加",description = "模块",required = true,type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN save() {
        SysRolesEntity entity = ObjectUtil.getObjectFromMap(SysRolesEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    @API(name="删除",
        tag = {
            @Tag(name = "[系统管理]角色",description = "SysRoles")
        },
        method = API.Method.POST,
        summary = "删除[系统管理]角色",
        description = "删除[系统管理]角色",
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
            dao.deleteInBatch(SysRolesEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    @API(name="更新",
        tag = {
            @Tag(name = "[系统管理]角色",description = "SysRoles")
        },
        method = API.Method.POST,
        summary = "更新[系统管理]角色",
        description = "更新[系统管理]角色",
        parameters = {
            @Param(name = "sysRolesId",in = "更新",description = "角色唯一标识",required = true,type = Param.Type.STRING),
            @Param(name = "roleName",in = "更新",description = "角色名称",type = Param.Type.STRING),
            @Param(name = "roleDesc",in = "更新",description = "角色说明",type = Param.Type.STRING),
            @Param(name = "enable",in = "更新",description = "是否可用",type = Param.Type.STRING),
            @Param(name = "issys",in = "更新",description = "是否系统权限",type = Param.Type.STRING),
            @Param(name = "moduleId",in = "更新",description = "模块",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN update() {
        SysRolesEntity entity = ObjectUtil.getObjectFromMap(SysRolesEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysRolesId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    @API(name="查询",
        tag = {
            @Tag(name = "[系统管理]角色",description = "SysRoles")
        },
        method = API.Method.GET,
        summary = "查询[系统管理]角色",
        description = "查询[系统管理]角色",
        parameters = {
            @Param(name = "page",in = "查询",description = "第几页",type = Param.Type.INTEGER),
            @Param(name = "size",in = "查询",description = "每页条数",type = Param.Type.INTEGER)
        },
        responses = {
        @Responses(code = "000001",description = "成功",schema = SysRolesEntity.class,isArray = true),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(SysRolesEntity.class,getInParam("page",Integer.class,0),getInParam("size",Integer.class,10)));
        return RETURN.SUCCESS;
    }
}
