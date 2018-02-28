package com.agile.mvc.service;

import com.agile.common.annotation.*;
import com.agile.common.service.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.DictionaryDataEntity;

/**
 * Created by 佟盟
 */
@Service
public class DictionaryDataService extends MainService {

    @API(name="保存",
        tag = {
            @Tag(name = "[系统管理]字典数据表",description = "DictionaryData")
        },
        method = API.Method.POST,
        summary = "新增[系统管理]字典数据表",
        description = "新增[系统管理]字典数据表",
        parameters = {

            @Param(name = "dicCode",in = "添加",description = "字典表_字典编码",required = true,type = Param.Type.STRING),
            @Param(name = "name",in = "添加",description = "字典值显示名称",type = Param.Type.STRING),
            @Param(name = "value",in = "添加",description = "字典值代表值",type = Param.Type.STRING),
            @Param(name = "isFixed",in = "添加",description = "字典值是否固定",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN save() {
        DictionaryDataEntity entity = ObjectUtil.getObjectFromMap(DictionaryDataEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    @API(name="删除",
        tag = {
            @Tag(name = "[系统管理]字典数据表",description = "DictionaryData")
        },
        method = API.Method.POST,
        summary = "删除[系统管理]字典数据表",
        description = "删除[系统管理]字典数据表",
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
            dao.deleteInBatch(DictionaryDataEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    @API(name="更新",
        tag = {
            @Tag(name = "[系统管理]字典数据表",description = "DictionaryData")
        },
        method = API.Method.POST,
        summary = "更新[系统管理]字典数据表",
        description = "更新[系统管理]字典数据表",
        parameters = {
            @Param(name = "code",in = "更新",description = "字典编码",required = true,type = Param.Type.STRING),
            @Param(name = "dicCode",in = "更新",description = "字典表_字典编码",type = Param.Type.STRING),
            @Param(name = "name",in = "更新",description = "字典值显示名称",type = Param.Type.STRING),
            @Param(name = "value",in = "更新",description = "字典值代表值",type = Param.Type.STRING),
            @Param(name = "isFixed",in = "更新",description = "字典值是否固定",type = Param.Type.STRING),
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN update() {
        DictionaryDataEntity entity = ObjectUtil.getObjectFromMap(DictionaryDataEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getCode())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    @API(name="查询",
        tag = {
            @Tag(name = "[系统管理]字典数据表",description = "DictionaryData")
        },
        method = API.Method.GET,
        summary = "查询[系统管理]字典数据表",
        description = "查询[系统管理]字典数据表",
        parameters = {
            @Param(name = "page",in = "查询",description = "第几页",type = Param.Type.INTEGER),
            @Param(name = "size",in = "查询",description = "每页条数",type = Param.Type.INTEGER)
        },
        responses = {
        @Responses(code = "000001",description = "成功",schema = DictionaryDataEntity.class,isArray = true),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(DictionaryDataEntity.class,getInParam("page",Integer.class,0),getInParam("size",Integer.class,10)));
        return RETURN.SUCCESS;
    }
    void task1(){
        System.out.println(1111111);
    }
    void task2(){
        System.out.println(2222222);
    }
}
