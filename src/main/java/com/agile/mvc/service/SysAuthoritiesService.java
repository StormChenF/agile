package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import com.agile.mvc.model.dao.SysAuthoritiesRepository;
import com.agile.mvc.model.entity.SysAuthoritiesEntity;

/**
* Created by 佟盟
*/
@Service
public class SysAuthoritiesService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/SysAuthoritiesService/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        SysAuthoritiesRepository dao = (SysAuthoritiesRepository) FactoryUtil.getBean("SysAuthoritiesRepository");
        SysAuthoritiesEntity entity = (SysAuthoritiesEntity)ObjectUtil.getObjectFromMap(SysAuthoritiesEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/SysAuthoritiesService/delete
     */
    public RETURN delete(){
        SysAuthoritiesRepository dao = (SysAuthoritiesRepository) FactoryUtil.getBean("SysAuthoritiesRepository");
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
        SysAuthoritiesRepository dao = (SysAuthoritiesRepository) FactoryUtil.getBean("SysAuthoritiesRepository");
        SysAuthoritiesEntity entity = (SysAuthoritiesEntity)ObjectUtil.getObjectFromMap(SysAuthoritiesEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/SysAuthoritiesService/query
     */
    public RETURN query(){
        SysAuthoritiesRepository dao = (SysAuthoritiesRepository) FactoryUtil.getBean("SysAuthoritiesRepository");
        dao.findAll(new PageRequest(Integer.parseInt(this.getInParam("page").toString()),Integer.parseInt(this.getInParam("size").toString())));
        return RETURN.SUCCESS;
    }
}
