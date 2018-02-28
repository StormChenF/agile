package com.agile.common.cache.redis;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by 佟盟 on 2018/2/9
 */
public abstract class RedisGeneralDataRegion extends RedisDataRegion implements GeneralDataRegion {
    private static final Logger log = LoggerFactory.getLogger(org.hibernate.cache.redis.regions.RedisGeneralDataRegion.class);

    protected RedisGeneralDataRegion(RedisAccessStrategyFactory accessStrategyFactory, RedisClient redis, String regionName, Properties props, RedisCacheTimeStamper timestamper) {
        super(accessStrategyFactory, redis, regionName, props, timestamper);
    }

    public Object get(SharedSessionContractImplementor session, Object key){
        return this.get(key);
    }

    public void put(SharedSessionContractImplementor session, Object key, Object value){
        this.put(key,value);
    }

    public Object get(Object key) {
        if (key == null) {
            return null;
        } else {
            try {
                Object value = this.redis.get(this.getName(), key, this.getExpireInSeconds());
                log.trace("get cache item... key=[{}], value=[{}]", key, value);
                return value;
            } catch (Exception var3) {
                log.warn("Fail to get cache item... key=" + key, var3);
                return null;
            }
        }
    }

    public void put(Object key, Object value) {
        try {
            this.redis.set(this.getName(), key, value, (long)this.getExpireInSeconds());
        } catch (Exception var4) {
            log.warn("Fail to put cache item... key=" + key, var4);
        }

    }

    public void evict(Object key) {
        try {
            this.redis.del(this.getName(), key);
        } catch (Exception var3) {
            log.warn("Fail to remove cache item... key=" + key, var3);
        }

    }

    public void evictAll() {
        try {
            this.redis.deleteRegion(this.getName());
        } catch (Exception var2) {
            log.warn("Fail to remove cache items... region=" + this.getName(), var2);
        }

    }
}
