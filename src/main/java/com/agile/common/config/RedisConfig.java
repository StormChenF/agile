package com.agile.common.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by 佟盟 on 2017/10/8
 */
@Configuration
public class RedisConfig implements EnvironmentAware {
    private Environment env;

    @Bean
    JedisPoolConfig redisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(env.getProperty("agile.redis.max_idle",int.class));
        jedisPoolConfig.setMinIdle(env.getProperty("agile.redis.min_idle",int.class));
        jedisPoolConfig.setMaxWaitMillis(env.getProperty("agile.redis.max_wait_millis",int.class));
        jedisPoolConfig.setTestOnReturn(env.getProperty("agile.redis.test_on_return",boolean.class));
        jedisPoolConfig.setTestOnBorrow(env.getProperty("agile.redis.test_on_borrow",boolean.class));
        return jedisPoolConfig;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(env.getProperty("agile.redis.host"));
        jedisConnectionFactory.setPort(env.getProperty("agile.redis.port",int.class));
        jedisConnectionFactory.setPassword(env.getProperty("agile.redis.pass"));
        jedisConnectionFactory.setPoolConfig(redisPool());
        return jedisConnectionFactory;
    }

    @Bean
    StringRedisTemplate redisTemplate(){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory());
        return stringRedisTemplate;
    }

    @Bean
    RedisCacheManager redis(){
        return new RedisCacheManager(redisTemplate());
    }

    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }
}
