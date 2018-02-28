package com.agile.common.cache.redis;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.internal.DefaultCacheKeysFactory;
import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.access.SoftLock;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class ReadOnlyRedisEntityRegionAccessStrategy extends AbstractRedisAccessStrategy<RedisEntityRegion> implements EntityRegionAccessStrategy {
    public ReadOnlyRedisEntityRegionAccessStrategy(RedisEntityRegion region, SessionFactoryOptions settings) {
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
        return null;
    }

    public void unlockItem(Object key, SoftLock lock) {
        this.evict(key);
    }

    public boolean insert(Object key, Object value, Object version) {
        return false;
    }

    public boolean afterInsert(Object key, Object value, Object version) {
        ((RedisEntityRegion)this.region).put(key, value);
        return true;
    }

    public boolean update(Object key, Object value, Object currentVersion, Object previousVersion) {
        throw new UnsupportedOperationException("Can't write to a readonly object");
    }

    public boolean afterUpdate(Object key, Object value, Object currentVersion, Object previousVersion, SoftLock lock) {
        throw new UnsupportedOperationException("Can't write to a readonly object");
    }

    @Override
    public Object generateCacheKey(Object id, EntityPersister persister, SessionFactoryImplementor factory, String tenantIdentifier) {
        return  DefaultCacheKeysFactory.staticCreateEntityKey(id, persister, factory, tenantIdentifier);
    }

    @Override
    public Object getCacheKeyId(Object cacheKey) {
        return DefaultCacheKeysFactory.staticGetEntityId(cacheKey);
    }

    @Override
    public boolean insert(SharedSessionContractImplementor var1, Object var2, Object var3, Object var4) throws CacheException {
        return false;
    }

    @Override
    public boolean afterInsert(SharedSessionContractImplementor session, Object key, Object value, Object version) throws CacheException {
        this.region().put(key, value);
        return true;
    }

    @Override
    public boolean update(SharedSessionContractImplementor var1, Object var2, Object var3, Object var4, Object var5) throws CacheException {
        throw new UnsupportedOperationException("Can't write to a readonly object");
    }

    @Override
    public boolean afterUpdate(SharedSessionContractImplementor var1, Object var2, Object var3, Object var4, Object var5, SoftLock var6) throws CacheException {
        throw new UnsupportedOperationException("Can't write to a readonly object");
    }

    @Override
    public Object get(SharedSessionContractImplementor session, Object key, long txTimestamp) throws CacheException {
        return this.region().get(key);
    }

    @Override
    public boolean putFromLoad(SharedSessionContractImplementor var1, Object var2, Object var3, long var4, Object var6) throws CacheException {
        return false;
    }

    @Override
    public boolean putFromLoad(SharedSessionContractImplementor session, Object key, Object value, long txTimestamp, Object version, boolean minimalPutOverride) throws CacheException {
        if (minimalPutOverride && (this.region()).contains(key)) {
            return false;
        } else {
            (this.region()).put(key, value);
            return true;
        }
    }

    @Override
    public SoftLock lockItem(SharedSessionContractImplementor var1, Object var2, Object var3) throws CacheException {
        return null;
    }

    @Override
    public void unlockItem(SharedSessionContractImplementor var1, Object var2, SoftLock var3) throws CacheException {
        this.evict(var2);
    }

    @Override
    public void remove(SharedSessionContractImplementor var1, Object var2) throws CacheException {

    }
}
