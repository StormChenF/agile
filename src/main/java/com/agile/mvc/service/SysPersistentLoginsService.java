package com.agile.mvc.service;

import com.agile.common.service.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.SysPersistentLoginsEntity;

/**
 * Created by 佟盟
 */
@Service
public class SysPersistentLoginsService extends MainService {

    /**
     * 新增
     * 地址：http://localhost:8080/SysPersistentLoginsService/save
     */
    public RETURN save() {
        SysPersistentLoginsEntity entity = ObjectUtil.getObjectFromMap(SysPersistentLoginsEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/SysPersistentLoginsService/delete
     */
    public RETURN delete(){
        if (this.containsKey("id")){
            String[] ids = this.getInParamOfArray("id");
            dao.deleteInBatch(SysPersistentLoginsEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/SysUsersService/update
     */
    public RETURN update() {
        SysPersistentLoginsEntity entity = ObjectUtil.getObjectFromMap(SysPersistentLoginsEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysPersistentLoginsId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/SysPersistentLoginsService/query
     */
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(SysPersistentLoginsEntity.class,getInParam("page",Integer.class,0),getInParam("size",Integer.class,10)));
        return RETURN.SUCCESS;
    }
}
