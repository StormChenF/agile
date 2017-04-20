package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.SysTableRepository;
import com.agile.mvc.model.entity.SysTableEntity;

/**
* Created by 佟盟
*/
@Service
public class SysTableService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/SysTableService/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        SysTableRepository dao = (SysTableRepository) FactoryUtil.getBean("SysTableRepository");
        SysTableEntity entity = (SysTableEntity)ObjectUtil.getObjectFromMap(SysTableEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/SysTableService/delete
     */
    public RETURN delete(){
        SysTableRepository dao = (SysTableRepository) FactoryUtil.getBean("SysTableRepository");
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
        SysTableRepository dao = (SysTableRepository) FactoryUtil.getBean("SysTableRepository");
        SysTableEntity entity = (SysTableEntity)ObjectUtil.getObjectFromMap(SysTableEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/SysTableService/query
     */
    public RETURN query(){
        SysTableRepository dao = (SysTableRepository) FactoryUtil.getBean("SysTableRepository");
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
