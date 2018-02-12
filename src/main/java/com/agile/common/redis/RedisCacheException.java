package com.agile.common.redis;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class RedisCacheException extends RuntimeException {
    public RedisCacheException(Throwable e) {
        super(e);
    }

    public RedisCacheException(String s) {
        super(s);
    }

    public RedisCacheException(String s, Throwable e) {
        super(s, e);
    }
}

