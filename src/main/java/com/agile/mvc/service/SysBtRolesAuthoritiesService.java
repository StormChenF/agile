package com.agile.mvc.service;

import com.agile.common.server.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.SysBtRolesAuthoritiesRepository;
import com.agile.mvc.model.entity.SysBtRolesAuthoritiesEntity;

/**
* Created by 佟盟
*/
@Service
public class SysBtRolesAuthoritiesService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/SysBtRolesAuthoritiesService/save
     */
    public RETURN save() {
        SysBtRolesAuthoritiesRepository dao = FactoryUtil.getBean(SysBtRolesAuthoritiesRepository.class);
        SysBtRolesAuthoritiesEntity entity = ObjectUtil.getObjectFromMap(SysBtRolesAuthoritiesEntity.class, this.getInParam());
        if (entity.hashCode() == 0) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/SysBtRolesAuthoritiesService/delete
     */
    public RETURN delete(){
        SysBtRolesAuthoritiesRepository dao = FactoryUtil.getBean(SysBtRolesAuthoritiesRepository.class);
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
        SysBtRolesAuthoritiesRepository dao = FactoryUtil.getBean(SysBtRolesAuthoritiesRepository.class);
        SysBtRolesAuthoritiesEntity entity = ObjectUtil.getObjectFromMap(SysBtRolesAuthoritiesEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysBtRolesAuthoritiesId())) return RETURN.PARAMETER_ERROR;
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/SysBtRolesAuthoritiesService/query
     */
    public RETURN query(){
        SysBtRolesAuthoritiesRepository dao = FactoryUtil.getBean(SysBtRolesAuthoritiesRepository.class);
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
