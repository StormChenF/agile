package com.agile.common.redis;

import org.hibernate.cache.spi.TimestampsRegion;

import java.util.Properties;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class RedisTimestampsRegion extends RedisGeneralDataRegion implements TimestampsRegion {
    public RedisTimestampsRegion(RedisAccessStrategyFactory accessStrategyFactory, RedisClient redis, String regionName, Properties props, RedisCacheTimeStamper timestamper) {
        super(accessStrategyFactory, redis, regionName, props, timestamper);
    }
}
