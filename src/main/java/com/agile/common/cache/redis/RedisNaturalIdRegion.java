package com.agile.common.cache.redis;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.NaturalIdRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;

import java.util.Properties;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class RedisNaturalIdRegion extends RedisTransactionalDataRegion implements NaturalIdRegion {
    public RedisNaturalIdRegion(RedisAccessStrategyFactory accessStrategyFactory, RedisClient redis, String regionName, SessionFactoryOptions settings, CacheDataDescription metadata, Properties props, RedisCacheTimeStamper timestamper) {
        super(accessStrategyFactory, redis, regionName, settings, metadata, props, timestamper);
    }

    public NaturalIdRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
        return this.getAccessStrategyFactory().createNaturalIdRegionAccessStrategy(this, accessType);
    }
}
