package com.agile.common.config;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 佟盟 on 2017/10/8
 */
@Configuration
public class EhCacheConfig {


    @Bean
    EhCacheCacheManager ehCacheCacheManager(){
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(CacheManager.getCacheManager("agileManager"));
        ehCacheCacheManager.setTransactionAware(true);
        return ehCacheCacheManager;
    }

}
