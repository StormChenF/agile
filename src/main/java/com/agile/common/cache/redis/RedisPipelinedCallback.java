package com.agile.common.cache.redis;

import redis.clients.jedis.Pipeline;

/**
 * Created by 佟盟 on 2018/2/9
 */
public interface RedisPipelinedCallback {
    void execute(Pipeline var1);
}
