package com.agile.mvc.service;

import com.agile.common.server.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.DictionaryMainRepository;
import com.agile.mvc.model.entity.DictionaryMainEntity;

/**
* Created by 佟盟
*/
@Service
public class DictionaryMainService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/DictionaryMainService/save
     */
    public RETURN save() {
        DictionaryMainRepository dao = FactoryUtil.getBean(DictionaryMainRepository.class);
        DictionaryMainEntity entity = ObjectUtil.getObjectFromMap(DictionaryMainEntity.class, this.getInParam());
        if (entity.hashCode() == 0) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/DictionaryMainService/delete
     */
    public RETURN delete(){
        DictionaryMainRepository dao = FactoryUtil.getBean(DictionaryMainRepository.class);
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
        DictionaryMainRepository dao = FactoryUtil.getBean(DictionaryMainRepository.class);
        DictionaryMainEntity entity = ObjectUtil.getObjectFromMap(DictionaryMainEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getCode())) return RETURN.PARAMETER_ERROR;
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/DictionaryMainService/query
     */
    public RETURN query(){
        DictionaryMainRepository dao = FactoryUtil.getBean(DictionaryMainRepository.class);
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
