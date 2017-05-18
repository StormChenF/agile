package com.agile.common.base;

/**
 * Created by 佟盟 on 2017/5/18
 */
public interface AbstractCacheUtil {
    public void setCache(String key,Object object);
    public void setCache(String key,Object object,int timeToIdleSeconds);
    public void removeCache(String key);
    public void removeAll();
    public Object getCache(String key);
}
