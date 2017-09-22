package com.agile.mvc.service;

import com.agile.common.server.AgileMainService;
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
     * 地址：http://localhost:8080/LogMainService/save
     */
    public RETURN save() {
        LogMainRepository dao = FactoryUtil.getBean(LogMainRepository.class);
        LogMainEntity entity = ObjectUtil.getObjectFromMap(LogMainEntity.class, this.getInParam());
        if (entity.hashCode() == 0) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/LogMainService/delete
     */
    public RETURN delete(){
        LogMainRepository dao = FactoryUtil.getBean(LogMainRepository.class);
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
     * 地址：http://localhost:8080/SysUsersService/update
     */
    public RETURN update() {
        LogMainRepository dao = FactoryUtil.getBean(LogMainRepository.class);
        LogMainEntity entity = ObjectUtil.getObjectFromMap(LogMainEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getLogMainId())) return RETURN.PARAMETER_ERROR;
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/LogMainService/query
     */
    public RETURN query(){
        LogMainRepository dao = FactoryUtil.getBean(LogMainRepository.class);
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
