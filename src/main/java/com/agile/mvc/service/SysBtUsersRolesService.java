package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import com.agile.mvc.model.dao.SysBtUsersRolesRepository;
import com.agile.mvc.model.entity.SysBtUsersRolesEntity;

/**
* Created by 佟盟
*/
@Service
public class SysBtUsersRolesService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/SysBtUsersRolesService/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        SysBtUsersRolesRepository dao = (SysBtUsersRolesRepository) FactoryUtil.getBean("SysBtUsersRolesRepository");
        SysBtUsersRolesEntity entity = (SysBtUsersRolesEntity)ObjectUtil.getObjectFromMap(SysBtUsersRolesEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/SysBtUsersRolesService/delete
     */
    public RETURN delete(){
        SysBtUsersRolesRepository dao = (SysBtUsersRolesRepository) FactoryUtil.getBean("SysBtUsersRolesRepository");
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
        SysBtUsersRolesRepository dao = (SysBtUsersRolesRepository) FactoryUtil.getBean("SysBtUsersRolesRepository");
        SysBtUsersRolesEntity entity = (SysBtUsersRolesEntity)ObjectUtil.getObjectFromMap(SysBtUsersRolesEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/SysBtUsersRolesService/query
     */
    public RETURN query(){
        SysBtUsersRolesRepository dao = (SysBtUsersRolesRepository) FactoryUtil.getBean("SysBtUsersRolesRepository");
        dao.findAll(this.getPageInfo());
        return RETURN.SUCCESS;
    }
}
