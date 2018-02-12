package com.agile.common.redis;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.TransactionalDataRegion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class RedisTransactionalDataRegion  extends RedisDataRegion implements TransactionalDataRegion {
    private static final Logger log = LoggerFactory.getLogger(org.hibernate.cache.redis.regions.RedisTransactionalDataRegion.class);
    protected final SessionFactoryOptions settings;
    protected final CacheDataDescription metadata;

    public RedisTransactionalDataRegion(RedisAccessStrategyFactory accessStrategyFactory, RedisClient redis, String regionName, SessionFactoryOptions settings, CacheDataDescription metadata, Properties props, RedisCacheTimeStamper timestamper) {
        super(accessStrategyFactory, redis, regionName, props, timestamper);
        this.settings = settings;
        this.metadata = metadata;
    }

    public SessionFactoryOptions getSettings() {
        return this.settings;
    }

    public boolean isTransactionAware() {
        return false;
    }

    public CacheDataDescription getCacheDataDescription() {
        return this.metadata;
    }

    public Object get(Object key) {
        try {
            return this.redis.get(this.getName(), key, this.getExpireInSeconds());
        } catch (Exception var3) {
            log.warn("Fail to get cache item... key=" + key, var3);
            return null;
        }
    }

    public void put(Object key, Object value) {
        try {
            this.redis.set(this.getName(), key, value, (long)this.getExpireInSeconds());
        } catch (Exception var4) {
            log.warn("Fail to put cache item... key=" + key, var4);
        }

    }

    public void remove(Object key) throws CacheException {
        try {
            this.redis.del(this.getName(), key);
        } catch (Exception var3) {
            log.warn("Fail to remove cache item... key=" + key, var3);
        }

    }

    public void clear() {
        try {
            this.redis.deleteRegion(this.getName());
        } catch (Exception var2) {
            log.warn("Fail to clear region... name=" + this.getName(), var2);
        }

    }
}

