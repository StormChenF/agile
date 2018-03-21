package com.agile.mvc.service;

import com.agile.common.service.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.SysBtAuthoritiesResourcesEntity;

/**
 * Created by 佟盟
 */
@Service
public class SysBtAuthoritiesResourcesService extends MainService {

    /**
     * 新增
     * 地址：http://localhost:8080/SysBtAuthoritiesResourcesService/save
     */
    public RETURN save() {
        SysBtAuthoritiesResourcesEntity entity = ObjectUtil.getObjectFromMap(SysBtAuthoritiesResourcesEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/SysBtAuthoritiesResourcesService/delete
     */
    public RETURN delete(){
        if (this.containsKey("id")){
            String[] ids = this.getInParamOfArray("id");
            dao.deleteInBatch(SysBtAuthoritiesResourcesEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/SysUsersService/update
     */
    public RETURN update() {
        SysBtAuthoritiesResourcesEntity entity = ObjectUtil.getObjectFromMap(SysBtAuthoritiesResourcesEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysBtAuthoritiesResourcesId())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/SysBtAuthoritiesResourcesService/query
     */
    public RETURN query(){
        this.setOutParam("queryList",dao.findAll(SysBtAuthoritiesResourcesEntity.class,getInParam("page",Integer.class,0),getInParam("size",Integer.class,10)));
        return RETURN.SUCCESS;
    }
}
