package com.agile.mvc.service;

import com.agile.common.server.AgileMainService;
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
    public RETURN save() {
        LogTableRepository dao = FactoryUtil.getBean(LogTableRepository.class);
        LogTableEntity entity = ObjectUtil.getObjectFromMap(LogTableEntity.class, this.getInParam());
        if (entity.hashCode() == 0) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/LogTableService/delete
     */
    public RETURN delete(){
        LogTableRepository dao = FactoryUtil.getBean(LogTableRepository.class);
        if (this.containsKey("ids")){
            String[] ids = this.getInParamOfString("ids").split(",");
            for (int i = 0 ; i < ids.length ; i++) {
                dao.deleteById((Integer) ObjectUtil.cast(Integer.class,ids[i].trim()));
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
        LogTableRepository dao = FactoryUtil.getBean(LogTableRepository.class);
        LogTableEntity entity = ObjectUtil.getObjectFromMap(LogTableEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getLogTableId())) return RETURN.PARAMETER_ERROR;
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
