package com.agile.mvc.service;

import com.agile.common.server.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.SysBtRolesMoudlesRepository;
import com.agile.mvc.model.entity.SysBtRolesMoudlesEntity;

/**
* Created by 佟盟
*/
@Service
public class SysBtRolesMoudlesService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/SysBtRolesMoudlesService/save
     */
    public RETURN save() {
        SysBtRolesMoudlesRepository dao = FactoryUtil.getBean(SysBtRolesMoudlesRepository.class);
        SysBtRolesMoudlesEntity entity = ObjectUtil.getObjectFromMap(SysBtRolesMoudlesEntity.class, this.getInParam());
        if (entity.hashCode() == 0) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/SysBtRolesMoudlesService/delete
     */
    public RETURN delete(){
        SysBtRolesMoudlesRepository dao = FactoryUtil.getBean(SysBtRolesMoudlesRepository.class);
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
        SysBtRolesMoudlesRepository dao = FactoryUtil.getBean(SysBtRolesMoudlesRepository.class);
        SysBtRolesMoudlesEntity entity = ObjectUtil.getObjectFromMap(SysBtRolesMoudlesEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysBtRolesMoudlesId())) return RETURN.PARAMETER_ERROR;
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/SysBtRolesMoudlesService/query
     */
    public RETURN query(){
        SysBtRolesMoudlesRepository dao = FactoryUtil.getBean(SysBtRolesMoudlesRepository.class);
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
