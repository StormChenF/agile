package com.agile.mvc.service;

import com.agile.common.annotation.Init;
import com.agile.common.service.MainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.entity.DictionaryDataEntity;
import java.io.IOException;

/**
 * Created by 佟盟
 */
@Service
public class DictionaryDataService extends MainService {

    /**
     * 新增
     * 地址：http://localhost:8080/DictionaryDataService/save
     */
    public RETURN save() {
        DictionaryDataEntity entity = ObjectUtil.getObjectFromMap(DictionaryDataEntity.class, this.getInParam());
        if (!ObjectUtil.isValidity(entity)) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/DictionaryDataService/delete
     */
    public RETURN delete(){
        if (this.containsKey("id")){
            String[] ids = this.getInParamOfArray("id");
            dao.deleteInBatch(DictionaryDataEntity.class,ids);
            return RETURN.SUCCESS;
        }
        return RETURN.PARAMETER_ERROR;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/SysUsersService/update
     */
    public RETURN update() {
        DictionaryDataEntity entity = ObjectUtil.getObjectFromMap(DictionaryDataEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getCode())) return RETURN.PARAMETER_ERROR;
        dao.update(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/DictionaryDataService/query
     */
    public RETURN query() {
        this.setOutParam("forward","/SysUsersService/update");
        this.setOutParam("e",3);
//        this.setOutParam("queryList",dao.findAll(DictionaryDataEntity.class,getInParam("page",Integer.class,0),getInParam("size",Integer.class,10)));
        return RETURN.SUCCESS;
    }
    void task1(){
        System.out.println(1111111);
    }
    @Init
    void task2(){
        System.out.println(2222222);
    }
}
