package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.SysResourcesRepository;
import com.agile.mvc.model.entity.SysResourcesEntity;

/**
* Created by 佟盟
*/
@Service
public class SysResourcesService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/SysResourcesService/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        SysResourcesRepository dao = (SysResourcesRepository) FactoryUtil.getBean("SysResourcesRepository");
        SysResourcesEntity entity = (SysResourcesEntity)ObjectUtil.getObjectFromMap(SysResourcesEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/SysResourcesService/delete
     */
    public RETURN delete(){
        SysResourcesRepository dao = (SysResourcesRepository) FactoryUtil.getBean("SysResourcesRepository");
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
        SysResourcesRepository dao = (SysResourcesRepository) FactoryUtil.getBean("SysResourcesRepository");
        SysResourcesEntity entity = (SysResourcesEntity)ObjectUtil.getObjectFromMap(SysResourcesEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/SysResourcesService/query
     */
    public RETURN query(){
        SysResourcesRepository dao = (SysResourcesRepository) FactoryUtil.getBean("SysResourcesRepository");
        dao.findAll(this.getPageInfo());
        return RETURN.SUCCESS;
    }
}
