package ${servicePackage};

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.${className}Repository;
import com.agile.mvc.model.entity.${className}Entity;

/**
* Created by 佟盟
*/
@Service
public class ${className}Service extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/${className}Service/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        ${className}Repository dao = FactoryUtil.getBean(${className}Repository.class);
        ${className}Entity entity = ObjectUtil.getObjectFromMap(${className}Entity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/${className}Service/delete
     */
    public RETURN delete(){
        ${className}Repository dao = FactoryUtil.getBean(${className}Repository.class);
        String[] ids = this.getInParam("ids").toString().split(",");
        int frequency = ids.length;
        for (int i = 0 ; i < frequency ; i++) {
            dao.delete((Integer) ObjectUtil.cast(Integer.class,ids[i].trim()));
        }
        return RETURN.SUCCESS;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/agile/SysUsersService/update
     */
    public RETURN update() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        ${className}Repository dao = FactoryUtil.getBean(${className}Repository.class);
        ${className}Entity entity = ObjectUtil.getObjectFromMap(${className}Entity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/${className}Service/query
     */
    public RETURN query(){
        ${className}Repository dao = FactoryUtil.getBean(${className}Repository.class);
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
