package com.agile.common.cache.redis;

import redis.clients.jedis.Transaction;

/**
 * Created by 佟盟 on 2018/2/9
 */
public interface RedisTransactionalCallback {
    void execute(Transaction var1);
}
