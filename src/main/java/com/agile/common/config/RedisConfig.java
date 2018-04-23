package com.agile.common.config;

import com.agile.common.properties.RedisConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;
import java.util.List;

/**
 * Created by 佟盟 on 2017/10/8
 */
@Configuration
public class RedisConfig {

    @Bean
    public JedisPoolConfig redisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(RedisConfigProperties.getMaxIdle());
        jedisPoolConfig.setMinIdle(RedisConfigProperties.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(RedisConfigProperties.getMaxWaitMillis());
        jedisPoolConfig.setTestOnReturn(RedisConfigProperties.isTestOnReturn());
        jedisPoolConfig.setTestOnBorrow(RedisConfigProperties.isTestOnReturn());
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig redisPool){
        List<String> hosts = RedisConfigProperties.getHost();
        List<Integer> ports = RedisConfigProperties.getPort();
        if(hosts.size()>1){
            RedisSentinelConfiguration config = new RedisSentinelConfiguration()
                    .master("master");
            for(int i = 0 ; i < hosts.size();i++){
                config.sentinel(hosts.get(i),ports.get(i));
            }
            return new JedisConnectionFactory(config,redisPool);
        }else{
            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
            jedisConnectionFactory.setPassword(RedisConfigProperties.getPass());
            jedisConnectionFactory.setHostName(hosts.get(0));
            jedisConnectionFactory.setPort(ports.get(0));
            return jedisConnectionFactory;
        }
    }

    @Bean
    RedisTemplate redisTemplate(JedisPoolConfig redisPool){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory(redisPool));
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }
}
