package com.agile.mvc.service;

import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.LogMainEntity;

/**
 * Created by 佟盟
 */
@Service
public class LogMainService extends MainService {

    /**
     * 新增
     * 地址：http://localhost:8080/LogMainService/save
     */
    public RETURN save() throws IllegalAccessException {
        LogMainEntity entity = ObjectUtil.getObjectFromMap(LogMainEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/LogMainService/delete
     */
    public RETURN delete(){
        if (this.containsKey("ids")){
            String[] ids = this.getInParamOfString("ids").split(",");
            dao.deleteInBatch(LogMainEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/SysUsersService/update
     */
    public RETURN update() throws IllegalAccessException {
        LogMainEntity entity = ObjectUtil.getObjectFromMap(LogMainEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getLogMainId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/LogMainService/query
     */
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(LogMainEntity.class,0,10));
        return RETURN.SUCCESS;
    }
}
