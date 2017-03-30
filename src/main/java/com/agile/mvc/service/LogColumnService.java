package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import com.agile.mvc.model.dao.LogColumnRepository;
import com.agile.mvc.model.entity.LogColumnEntity;

/**
* Created by 佟盟
*/
@Service
public class LogColumnService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/LogColumnService/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        LogColumnRepository dao = (LogColumnRepository) FactoryUtil.getBean("LogColumnRepository");
        LogColumnEntity entity = (LogColumnEntity)ObjectUtil.getObjectFromMap(LogColumnEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/LogColumnService/delete
     */
    public RETURN delete(){
        LogColumnRepository dao = (LogColumnRepository) FactoryUtil.getBean("LogColumnRepository");
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
        LogColumnRepository dao = (LogColumnRepository) FactoryUtil.getBean("LogColumnRepository");
        LogColumnEntity entity = (LogColumnEntity)ObjectUtil.getObjectFromMap(LogColumnEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/LogColumnService/query
     */
    public RETURN query(){
        LogColumnRepository dao = (LogColumnRepository) FactoryUtil.getBean("LogColumnRepository");
        dao.findAll(this.getPageInfo());
        return RETURN.SUCCESS;
    }
}
