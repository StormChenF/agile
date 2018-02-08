package com.agile.common.factory;


import org.hibernate.cache.CacheException;
import org.hibernate.cache.redis.SingletonRedisRegionFactory;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cfg.Settings;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by 佟盟 on 2018/1/4
 */
@Component
public class RedisRegionFactory extends SingletonRedisRegionFactory {

    private static final long serialVersionUID = -4826505406683881209L;
    private CacheManager cacheManager;
    private static final AtomicLong CURRENT = new AtomicLong();
    protected Settings settings;

    @Override
    public void start(Settings settings, Properties properties) throws CacheException {
        cacheManager = RedisCacheManagerFactory.getCacheManager();
        this.settings = settings;
        Assert.notNull(cacheManager, "cacheManager is required,CacheManagerFactory must be init first");
    }

    @Override
    public void stop() {
        cacheManager = null;
    }

    @Override
    public boolean isMinimalPutsEnabledByDefault() {
        return true;
    }

    @Override
    public AccessType getDefaultAccessType() {
        return AccessType.NONSTRICT_READ_WRITE;
    }

    @Override
    public long nextTimestamp() {
        return CURRENT.incrementAndGet();
    }
//
//    @Override
//    public EntityRegion buildEntityRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
//        return new EntityRegionImpl(regionName, cacheManager.getCache(shortRegionName(regionName)), settings, properties, metadata);
//    }
//
//    @Override
//    public NaturalIdRegion buildNaturalIdRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
//        return new NaturalIdRegionImpl(regionName, cacheManager.getCache(shortRegionName(regionName)), settings, properties, metadata);
//    }
//
//    @Override
//    public CollectionRegion buildCollectionRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
//        return new CollectionRegionImpl(regionName, cacheManager.getCache(shortRegionName(regionName)), settings, properties, metadata);
//    }
//
//    @Override
//    public QueryResultsRegion buildQueryResultsRegion(String regionName, Properties properties) throws CacheException {
//        return new QueryResultsRegionImpl(regionName, cacheManager.getCache(shortRegionName(regionName)), settings, properties);
//    }
//
//    @Override
//    public TimestampsRegion buildTimestampsRegion(String regionName, Properties properties) throws CacheException {
//        return new TimestampsRegionImpl(regionName, cacheManager.getCache(shortRegionName(regionName)), settings, properties);
//    }
}
