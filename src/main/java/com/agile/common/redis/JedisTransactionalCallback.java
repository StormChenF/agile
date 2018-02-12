package com.agile.common.redis;

import redis.clients.jedis.Transaction;

/**
 * Created by 佟盟 on 2018/2/9
 */
public interface JedisTransactionalCallback {
    void execute(Transaction var1);
}
