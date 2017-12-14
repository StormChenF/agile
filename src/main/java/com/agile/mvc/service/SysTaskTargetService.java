package com.agile.mvc.service;

import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.SysTaskTargetEntity;

/**
 * Created by 佟盟
 */
@Service
public class SysTaskTargetService extends MainService {

    /**
     * 新增
     * 地址：http://localhost:8080/SysTaskTargetService/save
     */
    public RETURN save() throws IllegalAccessException {
        SysTaskTargetEntity entity = ObjectUtil.getObjectFromMap(SysTaskTargetEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/SysTaskTargetService/delete
     */
    public RETURN delete(){
        if (this.containsKey("ids")){
            String[] ids = this.getInParamOfString("ids").split(",");
            dao.deleteInBatch(SysTaskTargetEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/SysUsersService/update
     */
    public RETURN update() throws IllegalAccessException {
        SysTaskTargetEntity entity = ObjectUtil.getObjectFromMap(SysTaskTargetEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysTaskTargetId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/SysTaskTargetService/query
     */
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(SysTaskTargetEntity.class,0,10));
        return RETURN.SUCCESS;
    }
}
