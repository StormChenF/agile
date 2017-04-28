package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.LogMainRepository;
import com.agile.mvc.model.entity.LogMainEntity;

/**
* Created by 佟盟
*/
@Service
public class LogMainService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/LogMainService/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        LogMainRepository dao = (LogMainRepository) FactoryUtil.getBean("LogMainRepository");
        LogMainEntity entity = (LogMainEntity)ObjectUtil.getObjectFromMap(LogMainEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/LogMainService/delete
     */
    public RETURN delete(){
        LogMainRepository dao = (LogMainRepository) FactoryUtil.getBean("LogMainRepository");
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
        LogMainRepository dao = (LogMainRepository) FactoryUtil.getBean("LogMainRepository");
        LogMainEntity entity = (LogMainEntity)ObjectUtil.getObjectFromMap(LogMainEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/LogMainService/query
     */
    public RETURN query(){
        LogMainRepository dao = (LogMainRepository) FactoryUtil.getBean("LogMainRepository");
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
