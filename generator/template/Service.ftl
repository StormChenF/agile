package ${servicePackage};

import com.agile.common.service.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.${className}Entity;

/**
 * Created by 佟盟
 */
@Service
public class ${className}Service extends MainService {

    /**
     * 新增
     * 地址：http://localhost:8080/${className}Service/save
     */
    public RETURN save() {
        ${className}Entity entity = ObjectUtil.getObjectFromMap(${className}Entity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/${className}Service/delete
     */
    public RETURN delete(){
        if (this.containsKey("id")){
            String[] ids = this.getInParamOfArray("id");
            dao.deleteInBatch(${className}Entity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/SysUsersService/update
     */
    public RETURN update() {
        ${className}Entity entity = ObjectUtil.getObjectFromMap(${className}Entity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.get${primaryKeyPropertyName}())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/${className}Service/query
     */
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(${className}Entity.class,getInParam("page",Integer.class,0),getInParam("size",Integer.class,10)));
        return RETURN.SUCCESS;
    }
}
