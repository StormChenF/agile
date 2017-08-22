package com.agile.mvc.service;

import com.agile.common.server.AgileMainService;
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
    public RETURN save() {
        LogValueRepository dao = FactoryUtil.getBean(LogValueRepository.class);
        LogValueEntity entity = ObjectUtil.getObjectFromMap(LogValueEntity.class, this.getInParam());
        if (entity.hashCode() == 0) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/LogValueService/delete
     */
    public RETURN delete(){
        LogValueRepository dao = FactoryUtil.getBean(LogValueRepository.class);
        if (this.containsKey("ids")){
            String[] ids = this.getInParamOfString("ids").split(",");
            for (int i = 0 ; i < ids.length ; i++) {
                dao.delete((Integer) ObjectUtil.cast(Integer.class,ids[i].trim()));
            }
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/agile/SysUsersService/update
     */
    public RETURN update() {
        LogValueRepository dao = FactoryUtil.getBean(LogValueRepository.class);
        LogValueEntity entity = ObjectUtil.getObjectFromMap(LogValueEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getLogValueId())) return RETURN.PARAMETER_ERROR;
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/LogValueService/query
     */
    public RETURN query(){
        LogValueRepository dao = FactoryUtil.getBean(LogValueRepository.class);
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
