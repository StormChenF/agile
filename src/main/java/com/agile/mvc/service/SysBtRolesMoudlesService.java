package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import com.agile.mvc.model.dao.SysBtRolesMoudlesRepository;
import com.agile.mvc.model.entity.SysBtRolesMoudlesEntity;

/**
* Created by 佟盟
*/
@Service
public class SysBtRolesMoudlesService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/SysBtRolesMoudlesService/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        SysBtRolesMoudlesRepository dao = (SysBtRolesMoudlesRepository) FactoryUtil.getBean("SysBtRolesMoudlesRepository");
        SysBtRolesMoudlesEntity entity = (SysBtRolesMoudlesEntity)ObjectUtil.getObjectFromMap(SysBtRolesMoudlesEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/SysBtRolesMoudlesService/delete
     */
    public RETURN delete(){
        SysBtRolesMoudlesRepository dao = (SysBtRolesMoudlesRepository) FactoryUtil.getBean("SysBtRolesMoudlesRepository");
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
        SysBtRolesMoudlesRepository dao = (SysBtRolesMoudlesRepository) FactoryUtil.getBean("SysBtRolesMoudlesRepository");
        SysBtRolesMoudlesEntity entity = (SysBtRolesMoudlesEntity)ObjectUtil.getObjectFromMap(SysBtRolesMoudlesEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/SysBtRolesMoudlesService/query
     */
    public RETURN query(){
        SysBtRolesMoudlesRepository dao = (SysBtRolesMoudlesRepository) FactoryUtil.getBean("SysBtRolesMoudlesRepository");
        dao.findAll(this.getPageInfo());
        return RETURN.SUCCESS;
    }
}
