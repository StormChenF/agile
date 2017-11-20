package com.agile.mvc.service;

import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.LogValueEntity;

/**
 * Created by 佟盟
 */
@Service
public class LogValueService extends MainService {

    /**
     * 新增
     * 地址：http://localhost:8080/LogValueService/save
     */
    public RETURN save() throws IllegalAccessException {
        LogValueEntity entity = ObjectUtil.getObjectFromMap(LogValueEntity.class, this.getInParam());
        if (entity.hashCode() == 0) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/LogValueService/delete
     */
    public RETURN delete(){
        if (this.containsKey("ids")){
            String[] ids = this.getInParamOfString("ids").split(",");
            dao.deleteInBatch(LogValueEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/SysUsersService/update
     */
    public RETURN update() throws IllegalAccessException {
        LogValueEntity entity = ObjectUtil.getObjectFromMap(LogValueEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getLogValueId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/LogValueService/query
     */
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(LogValueEntity.class,0,10));
        return RETURN.SUCCESS;
    }
}
