package com.agile.common.util;

import com.agile.common.exception.NotFoundCacheProxyException;
import com.agile.common.server.EhCacheService;
import com.agile.common.server.RedisService;

/**
 * Created by 佟盟 on 2017/5/19
 */
public class CacheUtil {

    public static AbstractCacheUtil cache;

    public static AbstractCacheUtil getCache(){
        if (ObjectUtil.isEmpty(cache)){
            String cacheProperty = PropertiesUtil.getProperty("agile.cache.proxy").toLowerCase();
            switch (cacheProperty){
                case "redis":
                    cache = FactoryUtil.getBean(RedisService.class);
                    break;
                case "ehcache":
                    cache = FactoryUtil.getBean(EhCacheService.class);
                    break;
                default:
                    try {
                        throw new NotFoundCacheProxyException();
                    } catch (NotFoundCacheProxyException notFoundCacheProxy) {
                        notFoundCacheProxy.printStackTrace();
                    }
            }
        }
        return cache;
    }

    public static void setCache(String key, Object object) {
        getCache().setCache(key, object);
    }

    public static void setCache(String key, Object object, int timeToIdleSeconds) {
        getCache().setCache(key, object, timeToIdleSeconds);

    }

    public static void removeCache(String key) {
        getCache().removeCache(key);
    }

    public static void removeAll() {
        getCache().removeAll();
    }

    public static Object getCache(String key) {
        return getCache().getCache(key);
    }
}
