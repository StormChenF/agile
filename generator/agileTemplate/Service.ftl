package ${servicePackage};

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

    /**
     * 新增
     * 地址：http://localhost:8080/${className}Service/save
     */
    public RETURN save() throws IllegalAccessException {
        ${className}Entity entity = ObjectUtil.getObjectFromMap(${className}Entity.class, this.getInParam());
        if (entity.hashCode() == 0) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/${className}Service/delete
     */
    public RETURN delete(){
        if (this.containsKey("ids")){
            String[] ids = this.getInParamOfString("ids").split(",");
            dao.deleteInBatch(${className}Entity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/SysUsersService/update
     */
    public RETURN update() throws IllegalAccessException {
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
        this.setOutParam("queryList",dao.findAll(${className}Entity.class,0,10));
        return RETURN.SUCCESS;
    }
}
