package com.agile.common.redis;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class JedisCacheException extends RuntimeException {
    public JedisCacheException(Throwable e) {
        super(e);
    }

    public JedisCacheException(String s) {
        super(s);
    }

    public JedisCacheException(String s, Throwable e) {
        super(s, e);
    }
}

