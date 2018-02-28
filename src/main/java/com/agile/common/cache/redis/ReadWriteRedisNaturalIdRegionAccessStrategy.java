package com.agile.common.cache.redis;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.NaturalIdRegion;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;
import org.hibernate.cache.spi.access.SoftLock;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.persister.entity.EntityPersister;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class ReadWriteRedisNaturalIdRegionAccessStrategy extends AbstractReadWriteRedisAccessStrategy<RedisNaturalIdRegion> implements NaturalIdRegionAccessStrategy {
    public ReadWriteRedisNaturalIdRegionAccessStrategy(RedisNaturalIdRegion region, SessionFactoryOptions settings) {
        super(region, settings);
    }

    public NaturalIdRegion getRegion() {
        return (NaturalIdRegion)this.region;
    }

    public boolean insert(Object key, Object value) {
        ((RedisNaturalIdRegion)this.region).put(key, value);
        return true;
    }

    public boolean afterInsert(Object key, Object value) {
        ((RedisNaturalIdRegion)this.region).put(key, value);
        return true;
    }

    public boolean update(Object key, Object value) {
        ((RedisNaturalIdRegion)this.region).put(key, value);
        return true;
    }

    public boolean afterUpdate(Object key, Object value, SoftLock lock) {
        ((RedisNaturalIdRegion)this.region).put(key, value);
        return true;
    }

    @Override
    public Object generateCacheKey(Object[] objects, EntityPersister entityPersister, SharedSessionContractImplementor sharedSessionContractImplementor) {
        return null;
    }

    @Override
    public Object[] getNaturalIdValues(Object o) {
        return new Object[0];
    }

    @Override
    public boolean insert(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, Object o1) throws CacheException {
        return false;
    }

    @Override
    public boolean afterInsert(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, Object o1) throws CacheException {
        return false;
    }

    @Override
    public boolean update(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, Object o1) throws CacheException {
        return false;
    }

    @Override
    public boolean afterUpdate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, Object o1, SoftLock softLock) throws CacheException {
        return false;
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