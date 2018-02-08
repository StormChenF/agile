package ${servicePackage};

import com.agile.common.annotation.*;
import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.${className}Entity;

/**
 * Created by 佟盟
 */
@Service
public class ${className}Service extends MainService {

    @API(name="保存",
        tag = {
            @Tag(name = "${className}",description = "${className}")
        },
        method = API.Method.POST,
        summary = "新增${className}Entity",
        description = "新增${className}Entity",
        parameters = {
<#list columnList as property>
    <#if property.isPrimaryKey == "false">
            @Param(name = "${property.propertyName}",in = "添加",description = "${property.remarks}",<#if property.nullable == "true">required = ${property.nullable},</#if>type = Param.Type.STRING),</#if>
</#list>
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN save() throws IllegalAccessException {
        ${className}Entity entity = ObjectUtil.getObjectFromMap(${className}Entity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    @API(name="删除",
        tag = {
            @Tag(name = "${className}",description = "${className}")
        },
        method = API.Method.POST,
        summary = "删除${className}Entity",
        description = "删除${className}Entity",
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
            dao.deleteInBatch(${className}Entity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    @API(name="更新",
        tag = {
            @Tag(name = "${className}",description = "${className}")
        },
        method = API.Method.POST,
        summary = "更新${className}Entity",
        description = "更新${className}Entity",
        parameters = {
<#list columnList as property>
    <#if property.isPrimaryKey == "true">
            @Param(name = "${property.propertyName}",in = "更新",description = "${property.remarks}",required = true,type = Param.Type.STRING),
    <#else>
            @Param(name = "${property.propertyName}",in = "更新",description = "${property.remarks}",type = Param.Type.STRING),
    </#if>
</#list>
        },
        responses = {
        @Responses(code = "000001",description = "成功"),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN update() throws IllegalAccessException {
        ${className}Entity entity = ObjectUtil.getObjectFromMap(${className}Entity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.get${primaryKeyPropertyName}())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    @API(name="查询",
        tag = {
            @Tag(name = "${className}",description = "${className}")
        },
        method = API.Method.GET,
        summary = "查询${className}Entity",
        description = "查询${className}Entity",
        parameters = {
            @Param(name = "page",in = "查询",description = "第几页",required = false,type = Param.Type.INTEGER),
            @Param(name = "size",in = "查询",description = "每页条数",required = false,type = Param.Type.INTEGER)
        },
        responses = {
        @Responses(code = "000001",description = "成功",schema = ${className}Entity.class,isArray = true),
        @Responses(code = "300000",description = "系统程序异常")
    })
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(${className}Entity.class,getInParamOfInteger("page",0),getInParamOfInteger("size",10)));
        return RETURN.SUCCESS;
    }
}
