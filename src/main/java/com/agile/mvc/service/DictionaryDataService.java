package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.agile.mvc.model.dao.DictionaryDataRepository;
import com.agile.mvc.model.entity.DictionaryDataEntity;

import java.util.Date;

/**
* Created by 佟盟
*/
@Service
public class DictionaryDataService extends AgileMainService {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/DictionaryDataService/save
     */
    public RETURN save() {
        DictionaryDataRepository dao = FactoryUtil.getBean(DictionaryDataRepository.class);
        DictionaryDataEntity entity = ObjectUtil.getObjectFromMap(DictionaryDataEntity.class, this.getInParam());
        if (entity.hashCode() == 0) return RETURN.PARAMETER_ERROR;
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/DictionaryDataService/delete
     */
    public RETURN delete(){
        DictionaryDataRepository dao = FactoryUtil.getBean(DictionaryDataRepository.class);
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
     * 地址：http://localhost:8080/agile/SysUsersService/update
     */
    public RETURN update() {
        DictionaryDataRepository dao = FactoryUtil.getBean(DictionaryDataRepository.class);
        DictionaryDataEntity entity = ObjectUtil.getObjectFromMap(DictionaryDataEntity.class, this.getInParam());
        if (ObjectUtil.isEmpty(entity.getCode())) return RETURN.PARAMETER_ERROR;
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/DictionaryDataService/query
     */
    public RETURN query(){
        DictionaryDataRepository dao = FactoryUtil.getBean(DictionaryDataRepository.class);
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }

//    @Scheduled(cron = "0/2 * * * * *")
    public static void main(String[] args) {
        // Create a cache manager
        final CacheManager cacheManager = new CacheManager();

        // create the cache called "helloworld"
        final Cache cache = cacheManager.getCache("securityCache");

        // create a key to map the data to
        final String key = "greeting";

        // Create a data element
        final Element putGreeting = new Element(key, "Hello, World!");

        // Put the element into the data store
        cache.put(putGreeting);

        // Retrieve the data element
        final Element getGreeting = cache.get(key);

        // Print the value
        System.out.println(getGreeting.getObjectValue());
    }


}
