package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
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
        SysBtUsersRolesRepository dao = FactoryUtil.getBean(SysBtUsersRolesRepository.class);
        SysBtUsersRolesEntity entity = ObjectUtil.getObjectFromMap(SysBtUsersRolesEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/SysBtUsersRolesService/delete
     */
    public RETURN delete(){
        SysBtUsersRolesRepository dao = FactoryUtil.getBean(SysBtUsersRolesRepository.class);
        String[] ids = this.getInParam("ids").toString().split(",");
        int frequency = ids.length;
        for (int i = 0 ; i < frequency ; i++) {
            dao.delete((Integer) ObjectUtil.cast(Integer.class,ids[i].trim()));
        }
        return RETURN.SUCCESS;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/agile/SysUsersService/update
     */
    public RETURN update() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        SysBtUsersRolesRepository dao = FactoryUtil.getBean(SysBtUsersRolesRepository.class);
        SysBtUsersRolesEntity entity = ObjectUtil.getObjectFromMap(SysBtUsersRolesEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/SysBtUsersRolesService/query
     */
    public RETURN query(){
        SysBtUsersRolesRepository dao = FactoryUtil.getBean(SysBtUsersRolesRepository.class);
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
