package com.agile.common.cache.redis;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.redis.util.JedisTool;
import org.hibernate.cache.spi.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * Created by 佟盟 on 2018/2/9
 */
public abstract class RedisDataRegion implements Region {
    private static final String CACHE_LOCK_TIMEOUT_PROPERTY = "io.redis.hibernate.cache_lock_timeout";
    private static final int DEFAULT_CACHE_LOCK_TIMEOUT = 60000;
    private static final String EXPIRE_IN_SECONDS = "redis.expiryInSeconds";
    private static final Logger log = LoggerFactory.getLogger(org.hibernate.cache.redis.regions.RedisDataRegion.class);
    protected final RedisAccessStrategyFactory accessStrategyFactory;
    private final String name;
    protected final RedisClient redis;
    private final int cacheLockTimeout;
    private final int expireInSeconds;
    protected boolean regionDeleted = false;
    private RedisCacheTimeStamper timestamper;

    protected RedisDataRegion(RedisAccessStrategyFactory accessStrategyFactory, RedisClient redis, String regionName, Properties props, RedisCacheTimeStamper timestamper) {
        this.accessStrategyFactory = accessStrategyFactory;
        this.redis = redis;
        this.name = regionName;
        this.timestamper = timestamper;
        this.cacheLockTimeout = Integer.decode(props.getProperty("io.redis.hibernate.cache_lock_timeout", String.valueOf(60000)));
        this.expireInSeconds = JedisTool.getExpireInSeconds(props, this.name);
    }

    public RedisClient getRedis() {
        return this.redis;
    }

    public int getCacheLockTimeout() {
        return this.cacheLockTimeout;
    }

    public int getExpireInSeconds() {
        return this.expireInSeconds;
    }

    public boolean isRegionDeleted() {
        return this.regionDeleted;
    }

    public String getName() {
        return this.name;
    }

    public void destroy() throws CacheException {
        log.info("destroy region... but not delete redis key. region=[{}]", this.name);
    }

    public boolean contains(Object key) {
        try {
            boolean exists = this.redis.exists(this.name, key);
            log.debug("cache contains items? region=[{}], key=[{}], contains=[{}]", new Object[]{this.name, key, exists});
            return exists;
        } catch (Throwable var3) {
            log.warn("Fail to check contains key... region=" + this.name, var3);
            return false;
        }
    }

    public long getSizeInMemory() {
        try {
            return this.redis.dbSize();
        } catch (Throwable var2) {
            log.warn("Fail to get count of cache items. region=" + this.name, var2);
            return -1L;
        }
    }

    public long getElementCountInMemory() {
        try {
            return (long)this.redis.keysInRegion(this.name).size();
        } catch (Throwable var2) {
            log.warn("Fail to get count of cache items. region=" + this.name, var2);
            return -1L;
        }
    }

    public long getElementCountOnDisk() {
        return -1L;
    }

    public Map toMap() {
        try {
            return this.redis.hgetAll(this.name);
        } catch (Throwable var2) {
            log.warn("Fail to build CacheEntry. return EmptyMap.", var2);
            return Collections.emptyMap();
        }
    }

    public long nextTimestamp() {
        return this.timestamper.next();
    }

    public int getTimeout() {
        return this.cacheLockTimeout;
    }

    public RedisAccessStrategyFactory getAccessStrategyFactory() {
        return this.accessStrategyFactory;
    }
}
