package com.agile.common.util;

/**
 * Created by 佟盟 on 2017/5/19
 */
public class CacheUtil {

    public static AbstractCacheUtil cache;

    public static AbstractCacheUtil getCache(){
        if (ObjectUtil.isEmpty(cache)){
            String cacheProperty = "agile.cache.proxy";
            cache = (AbstractCacheUtil) FactoryUtil.getBean(PropertiesUtil.getProperty(cacheProperty)+"Service");
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
