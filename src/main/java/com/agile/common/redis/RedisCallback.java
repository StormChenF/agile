package com.agile.common.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by 佟盟 on 2018/2/9
 */
public interface RedisCallback<T> {
    T execute(Jedis var1);
}
