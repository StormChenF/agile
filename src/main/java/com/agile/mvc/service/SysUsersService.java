package com.agile.mvc.service;

import com.agile.common.annotation.*;
import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.SysUsersEntity;

/**
 * Created by 佟盟
 */
@Service
public class SysUsersService extends MainService {

    @API(name="保存",
        tag = {
            @Tag(name = "[系统管理]用户",description = "SysUsers")
        },
        method = API.Method.POST,
        summary = "新增[系统管理]用户",
        description = "新增[系统管理]用户",
        parameters = {

            @Param(name = "username",in = "添加",description = "用户名",type = Param.Type.STRING),
            @Param(name = "name",in = "添加",description = "用户姓名",required = true,type = Param.Type.STRING),
            @Param(name = "password",in = "添加",description = "密码",type = Param.Type.STRING),
            @Param(name = "dtCreate",in = "添加",description = "创建日期",required = true,type = Param.Type.STRING),
            @Param(name = "lastLogin",in = "添加",description = "最后登录日期",required = true,type = Param.Type.STRING),
            @Param(name = "deadline",in = "添加",description = "截止日期",required = true,type = Param.Type.STRING),
            @Param(name = "loginIp",in = "添加",description = "最后登录IP地址",required = true,type = Param.Type.STRING),
            @Param(name = "vQzjgid",in = "添加",description = "所属机构ID",required = true,type = Param.Type.STRING),
            @Param(name = "vQzjgmc",in = "添加",description = "所属机构名称",required = true,type = Param.Type.STRING),
            @Param(name = "depId",in = "添加",description = "地区编号",required = true,type = Param.Type.STRING),
            @Param(name = "depName",in = "添加",description = "地区名称",required = true,type = Param.Type.STRING),
            @Param(name = "enabled",in = "添加",description = "是否可用",required = true,type = Param.Type.STRING),
            @Param(name = "accountNonExpired",in = "添加",description = "用户是否过期",required = true,type = Param.Type.STRING),
            @Param(name = "accountNonLocked",in = "添加",description = "用户是否锁定",required = true,type = Param.Type.STRING),
            @Param(name = "credentialsNonExpired",in = "添加",description = "用户证书是否有效",required = true,type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN save() {
        SysUsersEntity entity = ObjectUtil.getObjectFromMap(SysUsersEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    @API(name="删除",
        tag = {
            @Tag(name = "[系统管理]用户",description = "SysUsers")
        },
        method = API.Method.POST,
        summary = "删除[系统管理]用户",
        description = "删除[系统管理]用户",
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
            dao.deleteInBatch(SysUsersEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    @API(name="更新",
        tag = {
            @Tag(name = "[系统管理]用户",description = "SysUsers")
        },
        method = API.Method.POST,
        summary = "更新[系统管理]用户",
        description = "更新[系统管理]用户",
        parameters = {
            @Param(name = "sysUsersId",in = "更新",description = "唯一标识",required = true,type = Param.Type.STRING),
            @Param(name = "username",in = "更新",description = "用户名",type = Param.Type.STRING),
            @Param(name = "name",in = "更新",description = "用户姓名",type = Param.Type.STRING),
            @Param(name = "password",in = "更新",description = "密码",type = Param.Type.STRING),
            @Param(name = "dtCreate",in = "更新",description = "创建日期",type = Param.Type.STRING),
            @Param(name = "lastLogin",in = "更新",description = "最后登录日期",type = Param.Type.STRING),
            @Param(name = "deadline",in = "更新",description = "截止日期",type = Param.Type.STRING),
            @Param(name = "loginIp",in = "更新",description = "最后登录IP地址",type = Param.Type.STRING),
            @Param(name = "vQzjgid",in = "更新",description = "所属机构ID",type = Param.Type.STRING),
            @Param(name = "vQzjgmc",in = "更新",description = "所属机构名称",type = Param.Type.STRING),
            @Param(name = "depId",in = "更新",description = "地区编号",type = Param.Type.STRING),
            @Param(name = "depName",in = "更新",description = "地区名称",type = Param.Type.STRING),
            @Param(name = "enabled",in = "更新",description = "是否可用",type = Param.Type.STRING),
            @Param(name = "accountNonExpired",in = "更新",description = "用户是否过期",type = Param.Type.STRING),
            @Param(name = "accountNonLocked",in = "更新",description = "用户是否锁定",type = Param.Type.STRING),
            @Param(name = "credentialsNonExpired",in = "更新",description = "用户证书是否有效",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN update() {
        SysUsersEntity entity = ObjectUtil.getObjectFromMap(SysUsersEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysUsersId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    @API(name="查询",
        tag = {
            @Tag(name = "[系统管理]用户",description = "SysUsers")
        },
        method = API.Method.GET,
        summary = "查询[系统管理]用户",
        description = "查询[系统管理]用户",
        parameters = {
            @Param(name = "page",in = "查询",description = "第几页",type = Param.Type.INTEGER),
            @Param(name = "size",in = "查询",description = "每页条数",type = Param.Type.INTEGER)
        },
        responses = {
        @Responses(code = "000001",description = "成功",schema = SysUsersEntity.class,isArray = true),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(SysUsersEntity.class,getInParam("page",Integer.class,0),getInParam("size",Integer.class,10)));
        return RETURN.SUCCESS;
    }
}
