package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
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
        SysPersistentLoginsRepository dao = FactoryUtil.getBean(SysPersistentLoginsRepository.class);
        SysPersistentLoginsEntity entity = ObjectUtil.getObjectFromMap(SysPersistentLoginsEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/SysPersistentLoginsService/delete
     */
    public RETURN delete(){
        SysPersistentLoginsRepository dao = FactoryUtil.getBean(SysPersistentLoginsRepository.class);
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
        SysPersistentLoginsRepository dao = FactoryUtil.getBean(SysPersistentLoginsRepository.class);
        SysPersistentLoginsEntity entity = ObjectUtil.getObjectFromMap(SysPersistentLoginsEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/SysPersistentLoginsService/query
     */
    public RETURN query(){
        SysPersistentLoginsRepository dao = FactoryUtil.getBean(SysPersistentLoginsRepository.class);
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
