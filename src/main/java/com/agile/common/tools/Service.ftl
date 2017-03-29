package ${servicePackage};

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import com.agile.mvc.model.dao.${tableName}Repository;

/**
* Created by 佟盟
*/
@Service
public class ${tableName}Service extends AgileMainService {

    public RETURN save(){
        ${tableName}Repository dao = (${tableName}Repository) FactoryUtil.getBean("${tableName}Repository");
        return RETURN.SUCCESS;
    }
    public RETURN delete(){
        ${tableName}Repository dao = (${tableName}Repository) FactoryUtil.getBean("${tableName}Repository");
        return RETURN.SUCCESS;
    }
    public RETURN update(){
        ${tableName}Repository dao = (${tableName}Repository) FactoryUtil.getBean("${tableName}Repository");
        return RETURN.SUCCESS;
    }
    public RETURN query(){
        ${tableName}Repository dao = (${tableName}Repository) FactoryUtil.getBean("${tableName}Repository");
        return RETURN.SUCCESS;
    }
}
