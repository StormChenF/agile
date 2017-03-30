package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
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
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        DictionaryMainRepository dao = (DictionaryMainRepository) FactoryUtil.getBean("DictionaryMainRepository");
        DictionaryMainEntity entity = (DictionaryMainEntity)ObjectUtil.getObjectFromMap(DictionaryMainEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/DictionaryMainService/delete
     */
    public RETURN delete(){
        DictionaryMainRepository dao = (DictionaryMainRepository) FactoryUtil.getBean("DictionaryMainRepository");
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
        DictionaryMainRepository dao = (DictionaryMainRepository) FactoryUtil.getBean("DictionaryMainRepository");
        DictionaryMainEntity entity = (DictionaryMainEntity)ObjectUtil.getObjectFromMap(DictionaryMainEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/DictionaryMainService/query
     */
    public RETURN query(){
        DictionaryMainRepository dao = (DictionaryMainRepository) FactoryUtil.getBean("DictionaryMainRepository");
        dao.findAll(this.getPageInfo());
        return RETURN.SUCCESS;
    }
}
