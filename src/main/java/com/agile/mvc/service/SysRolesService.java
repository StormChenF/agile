package com.agile.mvc.service;

import com.agile.common.server.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.SysRolesRepository;
import com.agile.mvc.model.entity.SysRolesEntity;

/**
* Created by 佟盟
*/
@Service
public class SysRolesService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/SysRolesService/save
     */
    public RETURN save() {
        SysRolesRepository dao = FactoryUtil.getBean(SysRolesRepository.class);
        SysRolesEntity entity = ObjectUtil.getObjectFromMap(SysRolesEntity.class, this.getInParam());
        if (entity.hashCode() == 0) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/SysRolesService/delete
     */
    public RETURN delete(){
        SysRolesRepository dao = FactoryUtil.getBean(SysRolesRepository.class);
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
        SysRolesRepository dao = FactoryUtil.getBean(SysRolesRepository.class);
        SysRolesEntity entity = ObjectUtil.getObjectFromMap(SysRolesEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysRolesId())) return RETURN.PARAMETER_ERROR;
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/SysRolesService/query
     */
    public RETURN query(){
        SysRolesRepository dao = FactoryUtil.getBean(SysRolesRepository.class);
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
