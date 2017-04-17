package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.SysBtAuthoritiesResourcesRepository;
import com.agile.mvc.model.entity.SysBtAuthoritiesResourcesEntity;

/**
* Created by 佟盟
*/
@Service
public class SysBtAuthoritiesResourcesService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/SysBtAuthoritiesResourcesService/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        SysBtAuthoritiesResourcesRepository dao = (SysBtAuthoritiesResourcesRepository) FactoryUtil.getBean("SysBtAuthoritiesResourcesRepository");
        SysBtAuthoritiesResourcesEntity entity = (SysBtAuthoritiesResourcesEntity)ObjectUtil.getObjectFromMap(SysBtAuthoritiesResourcesEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/SysBtAuthoritiesResourcesService/delete
     */
    public RETURN delete(){
        SysBtAuthoritiesResourcesRepository dao = (SysBtAuthoritiesResourcesRepository) FactoryUtil.getBean("SysBtAuthoritiesResourcesRepository");
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
        SysBtAuthoritiesResourcesRepository dao = (SysBtAuthoritiesResourcesRepository) FactoryUtil.getBean("SysBtAuthoritiesResourcesRepository");
        SysBtAuthoritiesResourcesEntity entity = (SysBtAuthoritiesResourcesEntity)ObjectUtil.getObjectFromMap(SysBtAuthoritiesResourcesEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/SysBtAuthoritiesResourcesService/query
     */
    public RETURN query(){
        SysBtAuthoritiesResourcesRepository dao = (SysBtAuthoritiesResourcesRepository) FactoryUtil.getBean("SysBtAuthoritiesResourcesRepository");
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
