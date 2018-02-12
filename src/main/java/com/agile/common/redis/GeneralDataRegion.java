package com.agile.common.redis;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.Region;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

/**
 * Created by 佟盟 on 2018/2/9
 */
public interface GeneralDataRegion extends Region {
    Object get(SharedSessionContractImplementor var1, Object var2) throws CacheException;

    void put(SharedSessionContractImplementor var1, Object var2, Object var3) throws CacheException;

    void evict(Object var1) throws CacheException;

    void evictAll() throws CacheException;
}