package com.agile.common.redis;

import com.agile.common.util.CollectionsUtil;
import org.hibernate.cache.redis.serializer.SerializationTool;
import org.hibernate.cache.redis.serializer.SnappyRedisSerializer;
import org.hibernate.cache.redis.serializer.StringRedisSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class RedisClient {
    private RedisConnection connection;
    public static final int DEFAULT_EXPIRY_IN_SECONDS = 120;
    public static final String DEFAULT_REGION_NAME = "hibernate";
    private static final int MAX_TIMESTAMP_UPDATE_ATTEMPTS = 5;
    private static final Logger log = LoggerFactory.getLogger(RedisClient.class);
    private int expiryInSeconds;
    private final StringRedisSerializer regionSerializer;
    private final StringRedisSerializer keySerializer;
    private final SnappyRedisSerializer valueSerializer;

    public RedisClient(RedisConnection connection) {
        this.regionSerializer = new StringRedisSerializer();
        this.keySerializer = new StringRedisSerializer();
        this.valueSerializer = new SnappyRedisSerializer();
        this.connection = connection;
    }

    public int getExpiryInSeconds() {
        return this.expiryInSeconds;
    }

    public void setExpiryInSeconds(int expiryInSeconds) {
        this.expiryInSeconds = expiryInSeconds;
    }

    public String ping() {
        return this.run(jedis -> jedis.ping());
    }

    public Long dbSize() {
        return this.run(jedis -> jedis.dbSize());
    }

    public boolean exists(String region, Object key) {
        final byte[] rawRegion = this.rawRegion(region);
        final byte[] rawKey = this.rawKey(key);
        return this.run(jedis -> jedis.hexists(rawRegion, rawKey));
    }

    public Object get(String region, Object key) {
        return this.get(region, key, 0);
    }

    public Object get(final String region, Object key, final int expirationInSeconds) {
        final byte[] rawRegion = this.rawRegion(region);
        final byte[] rawKey = this.rawKey(key);
        if (expirationInSeconds > 0 && this.isExpired(region, key)) {
            this.runWithPipeline(pipeline -> {
                byte[] rawZkey = RedisClient.this.rawZkey(region);
                pipeline.zrem(rawZkey, new byte[][]{rawKey});
                pipeline.hdel(rawRegion, new byte[][]{rawKey});
            });
            return null;
        } else {
            byte[] rawValue = this.run(jedis -> jedis.hget(rawRegion, rawKey));
            if (rawValue != null && rawValue.length > 0 && expirationInSeconds > 0 && !region.contains("UpdateTimestampsCache")) {
                this.run((RedisCallback<Object>) jedis -> {
                    byte[] rawZkey = RedisClient.this.rawZkey(region);
                    long score = System.currentTimeMillis() + (long)expirationInSeconds * 1000L;
                    return jedis.zadd(rawZkey, (double)score, rawKey);
                });
            }

            return this.deserializeValue(rawValue);
        }
    }

    private Boolean isExpired(String region, Object key) {
        final byte[] rawZkey = this.rawZkey(region);
        final byte[] rawKey = this.rawKey(key);
        Double timestamp = this.run(jedis -> jedis.zscore(rawZkey, rawKey));
        return timestamp != null && System.currentTimeMillis() > timestamp.longValue();
    }

    Set keysInRegion(String region) {
        try {
            final byte[] rawRegion = this.rawRegion(region);
            Set rawKeys = this.run(jedis -> jedis.hkeys(rawRegion));
            if (rawKeys != null) {
                return this.deserializeKeys(rawKeys);
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return new HashSet();
    }

    public Long keySizeInRegion(String region) {
        final byte[] rawRegion = this.rawRegion(region);
        return this.run(redis -> redis.hlen(rawRegion));
    }

    //返回哈希表key中，所有的域和值
    Map<Object, Object> hgetAll(String region) {
        final byte[] rawRegion = this.rawRegion(region);
        Map rawMap = this.run(jedis -> jedis.hgetAll(rawRegion));
        HashMap map = new HashMap();

        for (Object o : rawMap.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            Object key = this.deserializeKey((byte[]) entry.getKey());
            Object value = this.deserializeValue((byte[]) entry.getValue());
            map.put(key, value);
        }

        return map;
    }

    //返回所有(一个或多个)给定 key 的值。
    public List mget(String region, Collection<?> keys) {
        final byte[] rawRegion = this.rawRegion(region);
        final byte[][] rawKeys = this.rawKeys(keys);
        List rawValues = this.run(redis -> redis.hmget(rawRegion, rawKeys));
        return this.deserializeValues(rawValues);
    }

    public void set(String region, Object key, Object value) {
        this.set(region, key, value, (long)this.expiryInSeconds, TimeUnit.SECONDS);
    }

    public void set(String region, Object key, Object value, long timeoutInSeconds) {
        this.set(region, key, value, timeoutInSeconds, TimeUnit.SECONDS);
    }

    public void set(final String region, Object key, Object value, long timeout, TimeUnit unit) {
        final byte[] rawRegion = this.rawRegion(region);
        final byte[] rawKey = this.rawKey(key);
        final byte[] rawValue = this.rawValue(value);
        final int seconds = (int)unit.toSeconds(timeout);
        this.runWithTx(tx -> {
            tx.hset(rawRegion, rawKey, rawValue);
            if (seconds > 0 && !region.contains("UpdateTimestampsCache")) {
                byte[] rawZkey = RedisClient.this.rawZkey(region);
                long score = System.currentTimeMillis() + (long)seconds * 1000L;
                tx.zadd(rawZkey, (double)score, rawKey);
            }

        });
    }

    void expire(String region) {
        try {
            final byte[] rawZkey = this.rawZkey(region);
            final byte[] rawRegion = this.rawRegion(region);
            final long score = System.currentTimeMillis();
            //返回有序集 key中，所有score值介于min和max之间(包括等于min或max)的成员。有序集成员按score值递增(从小到大)次序排列。
            final Set rawKeys = this.run(redis -> redis.zrangeByScore(rawZkey, 0.0D, (double)score));
            if (!CollectionsUtil.isEmpty(rawKeys)) {
                this.runWithPipeline(pipeline -> {
                    for (Object rawKey1 : rawKeys) {
                        byte[] rawKey = (byte[]) rawKey1;
                        //删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
                        pipeline.hdel(rawRegion, new byte[][]{rawKey});
                    }
                    //移除有序集 key 中，所有score值介于min和max之间(包括等于min或max)的成员。
                    pipeline.zremrangeByScore(rawZkey, 0.0D, (double)score);
                });
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }
    }

    public Long del(String region, Object key) {
        final byte[] rawRegion = this.rawRegion(region);
        final byte[] rawKey = this.rawKey(key);
        final byte[] rawZkey = this.rawZkey(region);
        this.runWithTx(tx -> {
            tx.hdel(rawRegion, new byte[][]{rawKey});
            //移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
            tx.zrem(rawZkey, new byte[][]{rawKey});
        });
        return 1L;
    }

    public void mdel(String region, Collection<?> keys) {
        final byte[] rawRegion = this.rawRegion(region);
        final byte[] rawZkey = this.rawZkey(region);
        final byte[][] rawKeys = this.rawKeys(keys);
        this.runWithTx(tx -> {
            byte[][] var2 = rawKeys;
            int var3 = var2.length;
            for (byte[] rawKey : var2) {
                tx.hdel(rawRegion, new byte[][]{rawKey});
                tx.zrem(rawZkey, new byte[][]{rawKey});
            }
        });
    }

    public void deleteRegion(String region) throws RedisCacheException {
        final byte[] rawRegion = this.rawRegion(region);
        final byte[] rawZkey = this.rawZkey(region);
        this.runWithTx(tx -> {
            //删除给定的一个或多个key
            tx.del(rawRegion);
            tx.del(rawZkey);
        });
    }

    public long nextTimeStamp(Object key) {
        byte[] rawKey = this.rawKey(key);
        Long updatedTimestamp = null;

        int updateAttempts;
        for(updateAttempts = 0; updatedTimestamp == null && updateAttempts < 5; ++updateAttempts) {
            updatedTimestamp = this.updateOrIncrementTimestamp(rawKey);
        }

        if (updatedTimestamp != null) {
            log.debug("updated timestamp: key=[{}], attempt=[{}], timestamp=[{}]", new Object[]{key, updateAttempts, updatedTimestamp});
            return updatedTimestamp;
        } else {
            Long incrementedTimestamp = this.incrementTimestamp(rawKey);
            log.warn("updated timestamp failed {} times. Fall back to incrementing: key=[{}], timestamp=[{}]", new Object[]{updateAttempts, key, incrementedTimestamp});
            return incrementedTimestamp;
        }
    }

    public void destroy() {
        if (this.connection != null) {
            this.connection.close();
        }
    }

    private Long updateOrIncrementTimestamp(final byte[] rawKey) {
        return this.run(jedis -> {
            jedis.watch(new byte[][]{rawKey});
            Long currentTimestamp = (Long) RedisClient.this.deserializeValue(jedis.get(rawKey));
            if (currentTimestamp == null) {
                currentTimestamp = 0L;
            }

            Long newTimestamp = Math.max(System.currentTimeMillis(), currentTimestamp) + 1L;
            Transaction tx = jedis.multi();
            tx.set(rawKey, RedisClient.this.rawValue(newTimestamp));
            List<Object> result = tx.exec();
            return result != null ? newTimestamp : null;
        });
    }

    private Long incrementTimestamp(final byte[] rawKey) {
        return this.run(jedis -> jedis.incr(rawKey));
    }

    public String flushDb() {
        log.info("Flush DB...");
        return this.run(jedis -> jedis.flushDB());
    }

    private byte[] rawKey(Object key) {
        return this.keySerializer.serialize(key.toString());
    }

    private byte[][] rawKeys(Collection<?> keys) {
        byte[][] rawKeys = new byte[keys.size()][];
        int i = 0;

        Object key;
        for(Iterator var4 = keys.iterator(); var4.hasNext(); rawKeys[i++] = this.rawKey(key)) {
            key = var4.next();
        }

        return rawKeys;
    }

    private byte[] rawZkey(String region) {
        return this.rawRegion("z:" + region);
    }

    private byte[] rawRegion(String region) {
        return this.regionSerializer.serialize(region);
    }

    private Object deserializeKey(byte[] rawKey) {
        return this.keySerializer.deserialize(rawKey);
    }

    private byte[] rawValue(Object value) {
        try {
            return this.valueSerializer.serialize(value);
        } catch (Exception var3) {
            return null;
        }
    }

    private Object deserializeValue(byte[] rawValue) {
        return this.valueSerializer.deserialize(rawValue);
    }

    private <T> T run(RedisCallback<T> callback) {
        Jedis jedis = (Jedis)this.connection.getNativeConnection();

        T var3;
        try {
            var3 = callback.execute(jedis);
        } finally {
            jedis.close();
        }

        return var3;
    }

    private List<Object> runWithTx(RedisTransactionalCallback callback) {
        Jedis jedis = (Jedis)this.connection.getNativeConnection();

        List<Object> var4;
        try {
            Transaction tx = jedis.multi();
            callback.execute(tx);
            var4 = tx.exec();
        } finally {
            jedis.close();
        }

        return var4;
    }

    private void runWithPipeline(RedisPipelinedCallback callback) {
        Jedis jedis = (Jedis)this.connection.getNativeConnection();

        try {
            Pipeline pipeline = jedis.pipelined();
            callback.execute(pipeline);
            pipeline.sync();
        } finally {
            jedis.close();
        }

    }

    private Set<Object> deserializeKeys(Set<byte[]> rawKeys) {
        Set<Object> keys = new HashSet<>();
        Iterator var3 = rawKeys.iterator();

        while(var3.hasNext()) {
            byte[] rawKey = (byte[])var3.next();
            keys.add(this.deserializeKey(rawKey));
        }

        return keys;
    }

    private List deserializeValues(List<byte[]> rawValues) {
        return SerializationTool.deserialize(rawValues, this.valueSerializer);
    }
}

