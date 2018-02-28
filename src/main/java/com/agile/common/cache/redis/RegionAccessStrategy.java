package com.agile.common.cache.redis;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.access.SoftLock;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

/**
 * Created by 佟盟 on 2018/2/9
 */
public interface RegionAccessStrategy {
    Object get(SharedSessionContractImplementor var1, Object var2, long var3) throws CacheException;

    boolean putFromLoad(SharedSessionContractImplementor var1, Object var2, Object var3, long var4, Object var6) throws CacheException;

    boolean putFromLoad(SharedSessionContractImplementor var1, Object var2, Object var3, long var4, Object var6, boolean var7) throws CacheException;

    SoftLock lockItem(SharedSessionContractImplementor var1, Object var2, Object var3) throws CacheException;

    SoftLock lockRegion() throws CacheException;

    void unlockItem(SharedSessionContractImplementor var1, Object var2, SoftLock var3) throws CacheException;

    void unlockRegion(SoftLock var1) throws CacheException;

    void remove(SharedSessionContractImplementor var1, Object var2) throws CacheException;

    void removeAll() throws CacheException;

    void evict(Object var1) throws CacheException;

    void evictAll() throws CacheException;
}
