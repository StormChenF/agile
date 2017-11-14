package com.agile.mvc.service;

import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.SysAuthoritiesRepository;
import com.agile.mvc.model.entity.SysAuthoritiesEntity;
import org.springframework.data.domain.PageRequest;

/**
 * Created by 佟盟
 */
@Service
public class SysAuthoritiesService extends MainService {

    /**
     * 新增
     * 地址：http://localhost:8080/SysAuthoritiesService/save
     */
    public RETURN save() throws IllegalAccessException {
        SysAuthoritiesRepository dao = FactoryUtil.getBean(SysAuthoritiesRepository.class);
        SysAuthoritiesEntity entity = ObjectUtil.getObjectFromMap(SysAuthoritiesEntity.class, this.getInParam());
        if (entity.hashCode() == 0) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/SysAuthoritiesService/delete
     */
    public RETURN delete(){
        SysAuthoritiesRepository dao = FactoryUtil.getBean(SysAuthoritiesRepository.class);
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
     * 地址：http://localhost:8080/SysUsersService/update
     */
    public RETURN update() throws IllegalAccessException {
        SysAuthoritiesRepository dao = FactoryUtil.getBean(SysAuthoritiesRepository.class);
        SysAuthoritiesEntity entity = ObjectUtil.getObjectFromMap(SysAuthoritiesEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysAuthorityId())) return RETURN.PARAMETER_ERROR;
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/SysAuthoritiesService/query
     */
    public RETURN query(){
        SysAuthoritiesRepository dao = FactoryUtil.getBean(SysAuthoritiesRepository.class);
        this.setOutParam("queryList",dao.findAll(PageRequest.of(0,10)));
        return RETURN.SUCCESS;
    }
}
