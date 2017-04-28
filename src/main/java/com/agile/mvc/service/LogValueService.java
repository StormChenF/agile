package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.LogValueRepository;
import com.agile.mvc.model.entity.LogValueEntity;

/**
* Created by 佟盟
*/
@Service
public class LogValueService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/LogValueService/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        LogValueRepository dao = (LogValueRepository) FactoryUtil.getBean("LogValueRepository");
        LogValueEntity entity = (LogValueEntity)ObjectUtil.getObjectFromMap(LogValueEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/LogValueService/delete
     */
    public RETURN delete(){
        LogValueRepository dao = (LogValueRepository) FactoryUtil.getBean("LogValueRepository");
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
        LogValueRepository dao = (LogValueRepository) FactoryUtil.getBean("LogValueRepository");
        LogValueEntity entity = (LogValueEntity)ObjectUtil.getObjectFromMap(LogValueEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/LogValueService/query
     */
    public RETURN query(){
        LogValueRepository dao = (LogValueRepository) FactoryUtil.getBean("LogValueRepository");
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
