package com.agile.mvc.service;

import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.SysBtTaskTargetEntity;

/**
 * Created by 佟盟
 */
@Service
public class SysBtTaskTargetService extends MainService {

    /**
     * 新增
     * 地址：http://localhost:8080/SysBtTaskTargetService/save
     */
    public RETURN save() throws IllegalAccessException {
        SysBtTaskTargetEntity entity = ObjectUtil.getObjectFromMap(SysBtTaskTargetEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/SysBtTaskTargetService/delete
     */
    public RETURN delete(){
        if (this.containsKey("ids")){
            String[] ids = this.getInParamOfString("ids").split(",");
            dao.deleteInBatch(SysBtTaskTargetEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/SysUsersService/update
     */
    public RETURN update() throws IllegalAccessException {
        SysBtTaskTargetEntity entity = ObjectUtil.getObjectFromMap(SysBtTaskTargetEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysBtTaskTargetId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/SysBtTaskTargetService/query
     */
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(SysBtTaskTargetEntity.class,0,10));
        return RETURN.SUCCESS;
    }
}
