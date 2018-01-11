package com.agile.common.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * Created by 佟盟 on 2018/1/5
 */
@Component
public class RedisCacheManagerFactory implements DisposableBean {
    private static CacheManager cacheManager;

    public static CacheManager getCacheManager() {
        return cacheManager;
    }

    @Autowired
    public static void setCacheManager(CacheManager cacheManager) {
        RedisCacheManagerFactory.cacheManager = cacheManager;
    }

    @Override
    public void destroy() throws Exception {

    }
}
