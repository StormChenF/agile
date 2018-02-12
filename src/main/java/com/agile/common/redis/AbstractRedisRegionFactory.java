package com.agile.common.redis;


import com.agile.common.config.RedisConfig;
import com.agile.common.properties.RedisConfigProperties;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.*;
import org.hibernate.cache.spi.access.AccessType;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;
import java.util.Properties;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by 佟盟 on 2018/2/9
 */
public abstract class AbstractRedisRegionFactory implements RegionFactory {
    private JedisConnectionFactory jedisConnectionFactory;
    protected SessionFactoryOptions settings;
    private final RedisAccessStrategyFactory accessStrategyFactory = new RedisAccessStrategyFactoryImpl();
    private final ConcurrentSkipListSet<String> regionNames = new ConcurrentSkipListSet<>();
    protected RedisClient redis = null;
    private RedisCacheTimeStamper timeStamper = null;
    private static Thread expirationThread = null;

    AbstractRedisRegionFactory() {
    }

    public boolean isMinimalPutsEnabledByDefault() {
        return true;
    }

    private void initConnectionFactory(){
        RedisConfig redisConfig = new RedisConfig();
        redisConfig.setProperties(new RedisConfigProperties());
        JedisPoolConfig jedisPoolConfig = redisConfig.redisPool();
        jedisConnectionFactory = redisConfig.jedisConnectionFactory(jedisPoolConfig);
    }

    void initializeRegionFactory(SessionFactoryOptions settings) {
        if (this.redis != null) {
            throw new IllegalStateException("Redis客户端已经启动");
        } else {
            initConnectionFactory();
            this.settings = settings;
            this.redis = JedisTool.createJedisClient(jedisConnectionFactory.getConnection());
            this.timeStamper = JedisTool.createTimestamper();
            this.startExpirationThread(this.redis);
        }
    }

    public AccessType getDefaultAccessType() {
        return AccessType.READ_WRITE;
    }

    public long nextTimestamp() {
        return this.timeStamper.next();
    }

    public EntityRegion buildEntityRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
        this.regionNames.add(regionName);
        return new RedisEntityRegion(this.accessStrategyFactory, this.redis, regionName, this.settings, metadata,properties, this.timeStamper);
    }

    public NaturalIdRegion buildNaturalIdRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
        this.regionNames.add(regionName);
        return new RedisNaturalIdRegion(this.accessStrategyFactory, this.redis, regionName, this.settings, metadata,properties, this.timeStamper);
    }

    public CollectionRegion buildCollectionRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
        this.regionNames.add(regionName);
        return new RedisCollectionRegion(this.accessStrategyFactory, this.redis, regionName, this.settings, metadata,properties, this.timeStamper);
    }

    public QueryResultsRegion buildQueryResultsRegion(String regionName, Properties properties) throws CacheException {
        this.regionNames.add(regionName);
        return new RedisQueryResultsRegion(this.accessStrategyFactory, this.redis, regionName,properties, this.timeStamper);
    }

    public TimestampsRegion buildTimestampsRegion(String regionName, Properties properties) throws CacheException {
        return new RedisTimestampsRegion(this.accessStrategyFactory, this.redis, regionName, properties, this.timeStamper);
    }

    protected void destroy() {
        if (expirationThread != null) {
            expirationThread.interrupt();
            expirationThread = null;
        }

        if (this.redis != null) {
            this.redis.destroy();
            this.redis = null;
        }

        this.timeStamper = null;
    }

    private synchronized void startExpirationThread(final RedisClient redis) {
        if (expirationThread == null || !expirationThread.isAlive()) {
            expirationThread = new Thread(() -> {
                while(true) {
                    try {
                        Thread.sleep(1000L);
                        ConcurrentSkipListSet regions = AbstractRedisRegionFactory.this.regionNames.clone();

                        for (Object region1 : regions) {
                            String region = (String) region1;
                            if (redis != null) {
                                try {
                                    redis.expire(region);
                                } catch (Exception var5) {
                                    var5.printStackTrace();
                                }
                            }
                        }
                    } catch (InterruptedException var6) {
                        return;
                    } catch (Exception var7) {
                        var7.printStackTrace();
                    }
                }
            });
            //设置守护线程
            expirationThread.setDaemon(true);
            expirationThread.start();
        }
    }
}
