package com.agile.common.cache.redis;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.spi.access.SoftLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 佟盟 on 2018/2/9
 */
public abstract class AbstractRedisAccessStrategy<T extends RedisTransactionalDataRegion> {
    private static final Logger log = LoggerFactory.getLogger(AbstractRedisAccessStrategy.class);
    protected final T region;
    protected final SessionFactoryOptions settings;

    AbstractRedisAccessStrategy(T region, SessionFactoryOptions settings) {
        this.region = region;
        this.settings = settings;
    }

    protected T region() {
        return this.region;
    }

    protected SessionFactoryOptions settings() {
        return this.settings;
    }

    public final boolean putFromLoad(Object key, Object value, long txTimestamp, Object version) {
        return this.putFromLoad(key, value, txTimestamp, version, this.settings.isMinimalPutsEnabled());
    }

    public abstract boolean putFromLoad(Object var1, Object var2, long var3, Object var5, boolean var6);

    public final SoftLock lockRegion() {
        return null;
    }

    public final void unlockRegion(SoftLock lock) {
        this.region.clear();
    }

    public void remove(Object key) {
    }

    public final void removeAll() {
        this.region.clear();
    }

    public final void evict(Object key) {
        this.region.remove(key);
    }

    public final void evictAll() {
        this.region.clear();
    }
}
