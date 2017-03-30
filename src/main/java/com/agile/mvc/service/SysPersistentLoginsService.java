package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import com.agile.mvc.model.dao.SysPersistentLoginsRepository;
import com.agile.mvc.model.entity.SysPersistentLoginsEntity;

/**
* Created by 佟盟
*/
@Service
public class SysPersistentLoginsService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/SysPersistentLoginsService/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        SysPersistentLoginsRepository dao = (SysPersistentLoginsRepository) FactoryUtil.getBean("SysPersistentLoginsRepository");
        SysPersistentLoginsEntity entity = (SysPersistentLoginsEntity)ObjectUtil.getObjectFromMap(SysPersistentLoginsEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/SysPersistentLoginsService/delete
     */
    public RETURN delete(){
        SysPersistentLoginsRepository dao = (SysPersistentLoginsRepository) FactoryUtil.getBean("SysPersistentLoginsRepository");
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
        SysPersistentLoginsRepository dao = (SysPersistentLoginsRepository) FactoryUtil.getBean("SysPersistentLoginsRepository");
        SysPersistentLoginsEntity entity = (SysPersistentLoginsEntity)ObjectUtil.getObjectFromMap(SysPersistentLoginsEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/SysPersistentLoginsService/query
     */
    public RETURN query(){
        SysPersistentLoginsRepository dao = (SysPersistentLoginsRepository) FactoryUtil.getBean("SysPersistentLoginsRepository");
        dao.findAll(this.getPageInfo());
        return RETURN.SUCCESS;
    }
}
