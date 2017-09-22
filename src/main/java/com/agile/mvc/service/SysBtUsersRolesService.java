package com.agile.mvc.service;

import com.agile.common.server.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.SysBtUsersRolesRepository;
import com.agile.mvc.model.entity.SysBtUsersRolesEntity;

/**
* Created by 佟盟
*/
@Service
public class SysBtUsersRolesService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/SysBtUsersRolesService/save
     */
    public RETURN save() {
        SysBtUsersRolesRepository dao = FactoryUtil.getBean(SysBtUsersRolesRepository.class);
        SysBtUsersRolesEntity entity = ObjectUtil.getObjectFromMap(SysBtUsersRolesEntity.class, this.getInParam());
        if (entity.hashCode() == 0) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/SysBtUsersRolesService/delete
     */
    public RETURN delete(){
        SysBtUsersRolesRepository dao = FactoryUtil.getBean(SysBtUsersRolesRepository.class);
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
        SysBtUsersRolesRepository dao = FactoryUtil.getBean(SysBtUsersRolesRepository.class);
        SysBtUsersRolesEntity entity = ObjectUtil.getObjectFromMap(SysBtUsersRolesEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysBtUsersRolesId())) return RETURN.PARAMETER_ERROR;
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/SysBtUsersRolesService/query
     */
    public RETURN query(){
        SysBtUsersRolesRepository dao = FactoryUtil.getBean(SysBtUsersRolesRepository.class);
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
