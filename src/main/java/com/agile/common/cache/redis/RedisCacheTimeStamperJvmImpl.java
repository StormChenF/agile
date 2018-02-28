package com.agile.common.cache.redis;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class RedisCacheTimeStamperJvmImpl implements RedisCacheTimeStamper {
    public long next() {
        return System.currentTimeMillis();
    }
}