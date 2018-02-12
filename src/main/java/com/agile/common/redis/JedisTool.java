package com.agile.common.redis;

import org.springframework.data.redis.connection.RedisConnection;
import redis.clients.jedis.JedisPoolConfig;
import java.util.Properties;

/**
 * Created by 佟盟 on 2018/2/9
 */
public final class JedisTool {
    private static final String EXPIRE_IN_SECONDS = "redis.expiryInSeconds";
    private static final String EXPIRY_PROPERTY_PREFIX = "redis.expiryInSeconds.";
    private static final String FILE_URL_PREFIX = "file:";
    public static final String TIMESTAMPER_PROPERTY_KEY = "redis.timestamper.class";
    public static final Class<?> DEFAULT_TIMESTAMPER_CLASS = RedisCacheTimeStamperJvmImpl.class;

    static RedisClient createJedisClient(RedisConnection connection) {
        return new RedisClient(connection);
    }

    static RedisCacheTimeStamper createTimestamper() {
        RedisCacheTimeStamperJvmImpl timestamper = new RedisCacheTimeStamperJvmImpl();
        return timestamper;
    }

    private static JedisPoolConfig createJedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(256);
        poolConfig.setMinIdle(2);
        return poolConfig;
    }

    public static int getExpireInSeconds(Properties props, String regionName) {
        int defaultExpiry = getDefaultExpireInSeconds(props);
        if (props == null) {
            return defaultExpiry;
        } else {
            int expireInSeconds = Integer.valueOf(props.getProperty("redis.expiryInSeconds." + regionName, String.valueOf(defaultExpiry)));
            return expireInSeconds;
        }
    }

    private static int getDefaultExpireInSeconds(Properties props) {
        return props == null ? 120 : Integer.decode(props.getProperty("redis.expiryInSeconds", String.valueOf(120)));
    }
}
