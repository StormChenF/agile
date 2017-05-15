package com.agile.common.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by 佟盟 on 2017/5/12
 */
public class CacheUtil{

    private static Cache cache = CacheManager.getInstance().getCache("agileCache");
    /**
     * 设置/更新缓存
     * @param key 索引值
     * @param object 缓存值
     */
    public static void setCache(String key,Object object){
        Element element = new Element(key, object);
        cache.put(element);
    }

    /**
     * 删除缓存
     * @param key 索引值
     */
    public static void removeCache(String key){
        cache.remove(key);
    }

    /**
     * 清空缓存
     */
    public static void removeAll(){
        cache.removeAll();
    }

    /**
     * 获取缓存
     * @param key 索引值
     * @return 缓存值
     */
    public static Object getCache(String key){
        Element element = cache.get(key);
        return ObjectUtil.isEmpty(element)?null:element.getObjectValue();
    }
}
