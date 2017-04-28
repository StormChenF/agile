package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.SysBtRolesAuthoritiesRepository;
import com.agile.mvc.model.entity.SysBtRolesAuthoritiesEntity;

/**
* Created by 佟盟
*/
@Service
public class SysBtRolesAuthoritiesService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/SysBtRolesAuthoritiesService/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        SysBtRolesAuthoritiesRepository dao = (SysBtRolesAuthoritiesRepository) FactoryUtil.getBean("SysBtRolesAuthoritiesRepository");
        SysBtRolesAuthoritiesEntity entity = (SysBtRolesAuthoritiesEntity)ObjectUtil.getObjectFromMap(SysBtRolesAuthoritiesEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/SysBtRolesAuthoritiesService/delete
     */
    public RETURN delete(){
        SysBtRolesAuthoritiesRepository dao = (SysBtRolesAuthoritiesRepository) FactoryUtil.getBean("SysBtRolesAuthoritiesRepository");
        String[] ids = this.getInParam("ids").toString().split(",");
        for (String id:ids) {
            dao.delete((Integer) ObjectUtil.cast(Integer.class,id.trim()));
        }
        return RETURN.SUCCESS;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/agile/SysUsersService/update
     */
    public RETURN update() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        SysBtRolesAuthoritiesRepository dao = (SysBtRolesAuthoritiesRepository) FactoryUtil.getBean("SysBtRolesAuthoritiesRepository");
        SysBtRolesAuthoritiesEntity entity = (SysBtRolesAuthoritiesEntity)ObjectUtil.getObjectFromMap(SysBtRolesAuthoritiesEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/SysBtRolesAuthoritiesService/query
     */
    public RETURN query(){
        SysBtRolesAuthoritiesRepository dao = (SysBtRolesAuthoritiesRepository) FactoryUtil.getBean("SysBtRolesAuthoritiesRepository");
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
