package com.agile.common.redis;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;

import java.util.Properties;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class RedisCollectionRegion extends RedisTransactionalDataRegion implements CollectionRegion {
    public RedisCollectionRegion(RedisAccessStrategyFactory accessStrategyFactory, RedisClient redis, String regionName, SessionFactoryOptions settings, CacheDataDescription metadata, Properties props, RedisCacheTimeStamper timestamper) {
        super(accessStrategyFactory, redis, regionName, settings, metadata, props, timestamper);
    }

    public CollectionRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
        return this.getAccessStrategyFactory().createCollectionRegionAccessStrategy(this, accessType);
    }
}
