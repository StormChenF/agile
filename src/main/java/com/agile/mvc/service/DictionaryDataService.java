package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.DictionaryDataRepository;
import com.agile.mvc.model.entity.DictionaryDataEntity;

import javax.xml.transform.Result;

/**
* Created by 佟盟
*/
@Api(value = "DictionaryDataService", description = "关于Restful接口文档注释")
@Service
public class DictionaryDataService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/DictionaryDataService/save
     */
    @ApiOperation(value = "GET获取数据",produces="application/json")
    @ApiResponse(code = 200, message = "success", response = Result.class)
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        DictionaryDataRepository dao = (DictionaryDataRepository) FactoryUtil.getBean("DictionaryDataRepository");
        DictionaryDataEntity entity = (DictionaryDataEntity)ObjectUtil.getObjectFromMap(DictionaryDataEntity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/DictionaryDataService/delete
     */
    public RETURN delete(){
        DictionaryDataRepository dao = (DictionaryDataRepository) FactoryUtil.getBean("DictionaryDataRepository");
        String[] ids = this.getInParam("ids").toString().split(",");
        for (String id:ids) {
            dao.delete((Integer) ObjectUtil.cast(Integer.class,id.trim()));
        }
        return RETURN.SUCCESS;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/agile/SysUsersService/update
     */
    public RETURN update() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        DictionaryDataRepository dao = (DictionaryDataRepository) FactoryUtil.getBean("DictionaryDataRepository");
        DictionaryDataEntity entity = (DictionaryDataEntity)ObjectUtil.getObjectFromMap(DictionaryDataEntity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/DictionaryDataService/query
     */
    public RETURN query(){
        DictionaryDataRepository dao = (DictionaryDataRepository) FactoryUtil.getBean("DictionaryDataRepository");
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
