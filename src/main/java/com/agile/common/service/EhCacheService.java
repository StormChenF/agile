package com.agile.common.service;

import com.agile.common.util.AbstractCacheUtil;
import com.agile.common.util.ObjectUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Service;

/**
 * Created by 佟盟 on 2017/5/12
 */
@Service
public class EhCacheService implements AbstractCacheUtil {

    private Cache getCache(){
        return CacheManager.getCacheManager("agileManager").getCache("agileCache");
    }
    /**
     * 设置/更新缓存
     *
     * @param key    索引值
     * @param object 缓存值
     */
    @Override
    public void setCache(String key, Object object) {
        Element element = new Element(key, object);
        getCache().put(element);
    }

    /**
     * 设置/更新缓存
     *
     * @param key    索引值
     * @param object 缓存值
     */
    @Override
    public void setCache(String key, Object object, int timeToIdleSeconds) {
        Element element = new Element(key, object);
        element.setTimeToIdle(timeToIdleSeconds);
        getCache().put(element);
    }

    /**
     * 删除缓存
     *
     * @param key 索引值
     */
    @Override
    public void removeCache(String key) {
        getCache().remove(key);
    }

    /**
     * 清空缓存
     */
    @Override
    public void removeAll() {
        getCache().removeAll();
    }

    /**
     * 获取缓存
     *
     * @param key 索引值
     * @return 缓存值
     */
    @Override
    public Object getCache(String key) {
        Element element = getCache().get(key);
        return ObjectUtil.isEmpty(element) ? null : element.getObjectValue();
    }
}
