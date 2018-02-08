package com.agile.common.server;

import com.agile.common.util.AbstractCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * Created by 佟盟 on 2017/5/18
 */
@Service
public class RedisService implements AbstractCacheUtil {

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    private Jedis getJedis() {
        if (jedis == null) {
            RedisConnection jedisConnection = jedisConnectionFactory.getConnection();
            jedis = (Jedis) jedisConnection.getNativeConnection();
            return jedis;
        }
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        RedisService.jedis = jedis;
    }

    private static Jedis jedis;
    @Override
    public void setCache(String key, Object object) {
        this.getJedis().set(key,String.valueOf(object));
    }

    @Override
    public void setCache(String key, Object object, int timeToIdleSeconds) {
        this.setCache(key, String.valueOf(object));
        this.getJedis().expire(key, timeToIdleSeconds);
    }

    @Override
    public void removeCache(String key) {
        this.getJedis().del(key);
    }

    @Override
    public void removeAll() {
        this.getJedis().flushDB();
    }

    @Override
    public Object getCache(String key) {
        return this.getJedis().get(key);
    }

    public Long setNx(String name,String time){
        return this.getJedis().setnx(name,time);
    }

    public void expire(String name, int time){
        this.getJedis().expire(name, time);
    }
}
