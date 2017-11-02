package ${servicePackage};

import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.${className}Repository;
import com.agile.mvc.model.entity.${className}Entity;
import org.springframework.data.domain.PageRequest;

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
        ${className}Repository dao = FactoryUtil.getBean(${className}Repository.class);
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
        ${className}Repository dao = FactoryUtil.getBean(${className}Repository.class);
        if (this.containsKey("ids")){
            String[] ids = this.getInParamOfString("ids").split(",");
            for (int i = 0 ; i < ids.length ; i++) {
                dao.deleteById((Integer) ObjectUtil.cast(Integer.class,ids[i].trim()));
            }
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/SysUsersService/update
     */
    public RETURN update() {
        ${className}Repository dao = FactoryUtil.getBean(${className}Repository.class);
        ${className}Entity entity = ObjectUtil.getObjectFromMap(${className}Entity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.get${primaryKeyPropertyName}())) return RETURN.PARAMETER_ERROR;
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/${className}Service/query
     */
    public RETURN query(){
        ${className}Repository dao = FactoryUtil.getBean(${className}Repository.class);
        this.setOutParam("queryList",dao.findAll(PageRequest.of(0,10)));
        return RETURN.SUCCESS;
    }
}
