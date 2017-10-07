package com.agile.common.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * Created by 佟盟 on 2017/10/8
 */
@Configuration
public class EhCacheConfig implements EnvironmentAware {

    private Environment env;

    @Bean
    @Primary
    EhCacheCacheManager ehCache(){
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(ehCacheManagerFactory().getObject());
        ehCacheCacheManager.setTransactionAware(true);
        return ehCacheCacheManager;
    }

    @Bean
    EhCacheManagerFactoryBean ehCacheManagerFactory(){
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setCacheManagerName("agileCacheManager");
        ehCacheManagerFactoryBean.setConfigLocation(resource());
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }

    private Resource resource(){
        return new ClassPathResource("com/agile/configure/agile-ehcache.xml");
    }
    @Override
    public void setEnvironment(@NotNull Environment environment) {
        env = environment;
    }
}
