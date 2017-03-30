package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.SysModulesRepository;
import com.agile.mvc.model.entity.SysModulesEntity;

/**
* Created by 佟盟
*/
@Service
public class SysModulesService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/SysModulesService/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        SysModulesRepository dao = (SysModulesRepository) FactoryUtil.getBean("SysModulesRepository");
        SysModulesEntity entity = (SysModulesEntity)ObjectUtil.getObjectFromMap(SysModulesEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/SysModulesService/delete
     */
    public RETURN delete(){
        SysModulesRepository dao = (SysModulesRepository) FactoryUtil.getBean("SysModulesRepository");
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
        SysModulesRepository dao = (SysModulesRepository) FactoryUtil.getBean("SysModulesRepository");
        SysModulesEntity entity = (SysModulesEntity)ObjectUtil.getObjectFromMap(SysModulesEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/SysModulesService/query
     */
    public RETURN query(){
        SysModulesRepository dao = (SysModulesRepository) FactoryUtil.getBean("SysModulesRepository");
        dao.findAll(this.getPageInfo());
        return RETURN.SUCCESS;
    }
}
