package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.SysUsersRepository;
import com.agile.mvc.model.entity.SysUsersEntity;

/**
* Created by 佟盟
*/
@Service
public class SysUsersService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/SysUsersService/save
     */
    public RETURN save() {
        SysUsersRepository dao = FactoryUtil.getBean(SysUsersRepository.class);
        SysUsersEntity entity = ObjectUtil.getObjectFromMap(SysUsersEntity.class, this.getInParam());
        if (entity.hashCode() == 0) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/SysUsersService/delete
     */
    public RETURN delete(){
        SysUsersRepository dao = FactoryUtil.getBean(SysUsersRepository.class);
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
        SysUsersRepository dao = FactoryUtil.getBean(SysUsersRepository.class);
        SysUsersEntity entity = ObjectUtil.getObjectFromMap(SysUsersEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysUsersId())) return RETURN.PARAMETER_ERROR;
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/SysUsersService/query
     */
    public RETURN query(){
        SysUsersRepository dao = FactoryUtil.getBean(SysUsersRepository.class);
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
