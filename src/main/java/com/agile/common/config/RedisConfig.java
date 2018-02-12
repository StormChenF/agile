package com.agile.common.config;

import com.agile.common.properties.RedisConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;

/**
 * Created by 佟盟 on 2017/10/8
 */
@Configuration
public class RedisConfig {
    @Autowired
    private RedisConfigProperties properties;

    @Bean
    public JedisPoolConfig redisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(properties.getMaxIdle());
        jedisPoolConfig.setMinIdle(properties.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(properties.getMaxWaitMillis());
        jedisPoolConfig.setTestOnReturn(properties.isTestOnReturn());
        jedisPoolConfig.setTestOnBorrow(properties.isTestOnReturn());
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig redisPool){
        List<String> hosts = properties.getHost();
        List<Integer> ports = properties.getPort();
        if(hosts.size()>1){
            RedisSentinelConfiguration config = new RedisSentinelConfiguration()
                    .master("master");
            for(int i = 0 ; i < hosts.size();i++){
                config.sentinel(hosts.get(i),ports.get(i));
            }
            config.setPassword(RedisPassword.of(properties.getPass()));
            return new JedisConnectionFactory(config,redisPool);
        }else{
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
            redisStandaloneConfiguration.setHostName(hosts.get(0));
            redisStandaloneConfiguration.setPort(ports.get(0));
            redisStandaloneConfiguration.setPassword(RedisPassword.of(properties.getPass()));
            JedisConnectionFactory je = new JedisConnectionFactory(redisStandaloneConfiguration);
            return je;
        }
    }

    @Bean
    RedisTemplate redisTemplate(JedisPoolConfig redisPool){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory(redisPool));
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager redisCacheManager(JedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(1))
                .disableCachingNullValues();
        return RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter(connectionFactory))
                .cacheDefaults(config)
                .withInitialCacheConfigurations(Collections.singletonMap("predefined", config.disableCachingNullValues()))
                .transactionAware()
                .build();
    }

    public void setProperties(RedisConfigProperties properties) {
        this.properties = properties;
    }
}
