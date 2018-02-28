package com.agile.common.cache.redis;

import org.hibernate.cache.spi.QueryResultsRegion;

import java.util.Properties;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class RedisQueryResultsRegion extends RedisGeneralDataRegion implements QueryResultsRegion {
    public RedisQueryResultsRegion(RedisAccessStrategyFactory accessStrategyFactory, RedisClient redis, String regionName, Properties props, RedisCacheTimeStamper timestamper) {
        super(accessStrategyFactory, redis, regionName, props, timestamper);
    }
}
