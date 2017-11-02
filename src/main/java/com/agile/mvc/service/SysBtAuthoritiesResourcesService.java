package com.agile.mvc.service;

import com.agile.common.server.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.SysBtAuthoritiesResourcesRepository;
import com.agile.mvc.model.entity.SysBtAuthoritiesResourcesEntity;
import org.springframework.data.domain.PageRequest;

/**
 * Created by 佟盟
 */
@Service
public class SysBtAuthoritiesResourcesService extends MainService {

    /**
     * 新增
     * 地址：http://localhost:8080/SysBtAuthoritiesResourcesService/save
     */
    public RETURN save() {
        SysBtAuthoritiesResourcesRepository dao = FactoryUtil.getBean(SysBtAuthoritiesResourcesRepository.class);
        SysBtAuthoritiesResourcesEntity entity = ObjectUtil.getObjectFromMap(SysBtAuthoritiesResourcesEntity.class, this.getInParam());
        if (entity.hashCode() == 0) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/SysBtAuthoritiesResourcesService/delete
     */
    public RETURN delete(){
        SysBtAuthoritiesResourcesRepository dao = FactoryUtil.getBean(SysBtAuthoritiesResourcesRepository.class);
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
    public RETURN update() {
        SysBtAuthoritiesResourcesRepository dao = FactoryUtil.getBean(SysBtAuthoritiesResourcesRepository.class);
        SysBtAuthoritiesResourcesEntity entity = ObjectUtil.getObjectFromMap(SysBtAuthoritiesResourcesEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getSysBtAuthoritiesResourcesId())) return RETURN.PARAMETER_ERROR;
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/SysBtAuthoritiesResourcesService/query
     */
    public RETURN query(){
        SysBtAuthoritiesResourcesRepository dao = FactoryUtil.getBean(SysBtAuthoritiesResourcesRepository.class);
        this.setOutParam("queryList",dao.findAll(PageRequest.of(0,10)));
        return RETURN.SUCCESS;
    }
}
