package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.LogTableRepository;
import com.agile.mvc.model.entity.LogTableEntity;

/**
* Created by 佟盟
*/
@Service
public class LogTableService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/LogTableService/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        LogTableRepository dao = FactoryUtil.getBean(LogTableRepository.class);
        LogTableEntity entity = ObjectUtil.getObjectFromMap(LogTableEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/LogTableService/delete
     */
    public RETURN delete(){
        LogTableRepository dao = FactoryUtil.getBean(LogTableRepository.class);
        String[] ids = this.getInParam("ids").toString().split(",");
        for (int i = 0 ; i < ids.length ; i++) {
            dao.delete((Integer) ObjectUtil.cast(Integer.class,ids[i].trim()));
        }
        return RETURN.SUCCESS;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/agile/SysUsersService/update
     */
    public RETURN update() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        LogTableRepository dao = FactoryUtil.getBean(LogTableRepository.class);
        LogTableEntity entity = ObjectUtil.getObjectFromMap(LogTableEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/LogTableService/query
     */
    public RETURN query(){
        LogTableRepository dao = FactoryUtil.getBean(LogTableRepository.class);
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
