package ${servicePackage};

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.${tableName}Repository;
import com.agile.mvc.model.entity.${tableName}Entity;

/**
* Created by 佟盟
*/
@Service
public class ${tableName}Service extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/${tableName}Service/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        ${tableName}Repository dao = (${tableName}Repository) FactoryUtil.getBean("${tableName}Repository");
        ${tableName}Entity entity = (${tableName}Entity)ObjectUtil.getObjectFromMap(${tableName}Entity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/${tableName}Service/delete
     */
    public RETURN delete(){
        ${tableName}Repository dao = (${tableName}Repository) FactoryUtil.getBean("${tableName}Repository");
        String[] ids = this.getInParam("id").toString().split(",");
        for (String id:ids) {
            dao.delete((Integer) ObjectUtil.cast(Integer.class,id));
        }
        return RETURN.SUCCESS;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/agile/SysUsersService/update
     */
    public RETURN update() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        ${tableName}Repository dao = (${tableName}Repository) FactoryUtil.getBean("${tableName}Repository");
        ${tableName}Entity entity = (${tableName}Entity)ObjectUtil.getObjectFromMap(${tableName}Entity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/${tableName}Service/query
     */
    public RETURN query(){
        ${tableName}Repository dao = (${tableName}Repository) FactoryUtil.getBean("${tableName}Repository");
        dao.findAll(this.getPageInfo());
        return RETURN.SUCCESS;
    }
}
