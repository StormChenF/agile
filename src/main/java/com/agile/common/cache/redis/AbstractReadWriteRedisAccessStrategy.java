package com.agile.common.cache.redis;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.spi.access.SoftLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class AbstractReadWriteRedisAccessStrategy<T extends RedisTransactionalDataRegion> extends AbstractRedisAccessStrategy<T> {
    private static final Logger log = LoggerFactory.getLogger(AbstractReadWriteRedisAccessStrategy.class);

    public AbstractReadWriteRedisAccessStrategy(T region, SessionFactoryOptions settings) {
        super(region, settings);
    }

    public final Object get(Object key, long txTimestamp) {
        return this.region.get(key);
    }

    public final boolean putFromLoad(Object key, Object value, long txTimestamp, Object version, boolean minimalPutOverride) {
        this.region.put(key, value);
        return true;
    }

    public final SoftLock lockItem(Object key, Object version) {
        this.region.remove(key);
        return null;
    }

    public final void unlockItem(Object key, SoftLock lock) {
        this.region.remove(key);
    }
}
