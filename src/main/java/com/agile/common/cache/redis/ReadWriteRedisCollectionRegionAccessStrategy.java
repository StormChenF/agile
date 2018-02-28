package com.agile.common.cache.redis;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;
import org.hibernate.cache.spi.access.SoftLock;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.persister.collection.CollectionPersister;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class ReadWriteRedisCollectionRegionAccessStrategy extends AbstractReadWriteRedisAccessStrategy<RedisCollectionRegion> implements CollectionRegionAccessStrategy {
    public ReadWriteRedisCollectionRegionAccessStrategy(RedisCollectionRegion region, SessionFactoryOptions settings) {
        super(region, settings);
    }

    public CollectionRegion getRegion() {
        return (CollectionRegion)this.region;
    }

    @Override
    public Object generateCacheKey(Object o, CollectionPersister collectionPersister, SessionFactoryImplementor sessionFactoryImplementor, String s) {
        return null;
    }

    @Override
    public Object getCacheKeyId(Object o) {
        return null;
    }

    @Override
    public Object get(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, long l) throws CacheException {
        return null;
    }

    @Override
    public boolean putFromLoad(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, Object o1, long l, Object o2) throws CacheException {
        return false;
    }

    @Override
    public boolean putFromLoad(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, Object o1, long l, Object o2, boolean b) throws CacheException {
        return false;
    }

    @Override
    public SoftLock lockItem(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, Object o1) throws CacheException {
        return null;
    }

    @Override
    public void unlockItem(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, SoftLock softLock) throws CacheException {

    }

    @Override
    public void remove(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws CacheException {

    }
}
