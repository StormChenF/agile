package com.agile.common.cache.redis;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;
import org.hibernate.cache.spi.access.SoftLock;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.persister.entity.EntityPersister;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class TransactionalRedisEntityRegionAccessStrategy extends AbstractRedisAccessStrategy<RedisEntityRegion> implements EntityRegionAccessStrategy {
    public TransactionalRedisEntityRegionAccessStrategy(RedisEntityRegion region, SessionFactoryOptions settings) {
        super(region, settings);
    }

    public EntityRegion getRegion() {
        return (EntityRegion)this.region;
    }

    public Object get(Object key, long txTimestamp) {
        return ((RedisEntityRegion)this.region).get(key);
    }

    public boolean putFromLoad(Object key, Object value, long txTimestamp, Object version, boolean minimalPutOverride) {
        if (minimalPutOverride && ((RedisEntityRegion)this.region).contains(key)) {
            return false;
        } else {
            ((RedisEntityRegion)this.region).put(key, value);
            return true;
        }
    }

    public SoftLock lockItem(Object key, Object version) {
        ((RedisEntityRegion)this.region).remove(key);
        return null;
    }

    public void unlockItem(Object key, SoftLock lock) {
        ((RedisEntityRegion)this.region).remove(key);
    }

    public boolean insert(Object key, Object value, Object version) {
        ((RedisEntityRegion)this.region).put(key, value);
        return true;
    }

    public boolean afterInsert(Object key, Object value, Object version) {
        return false;
    }

    public boolean update(Object key, Object value, Object currentVersion, Object previousVersion) {
        ((RedisEntityRegion)this.region).put(key, value);
        return true;
    }

    public boolean afterUpdate(Object key, Object value, Object currentVersion, Object previousVersion, SoftLock lock) {
        return false;
    }

    public void remove(Object key) {
        ((RedisEntityRegion)this.region).remove(key);
    }

    @Override
    public Object generateCacheKey(Object o, EntityPersister entityPersister, SessionFactoryImplementor sessionFactoryImplementor, String s) {
        return null;
    }

    @Override
    public Object getCacheKeyId(Object o) {
        return null;
    }

    @Override
    public boolean insert(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, Object o1, Object o2) throws CacheException {
        return false;
    }

    @Override
    public boolean afterInsert(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, Object o1, Object o2) throws CacheException {
        return false;
    }

    @Override
    public boolean update(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, Object o1, Object o2, Object o3) throws CacheException {
        return false;
    }

    @Override
    public boolean afterUpdate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, Object o1, Object o2, Object o3, SoftLock softLock) throws CacheException {
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