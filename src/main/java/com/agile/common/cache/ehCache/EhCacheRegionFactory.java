package com.agile.common.cache.ehCache;

import com.agile.common.config.EhCacheConfig;
import com.agile.common.factory.LoggerFactory;
import net.sf.ehcache.CacheManager;
import org.apache.commons.logging.Log;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.ehcache.internal.SingletonEhcacheRegionFactory;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 佟盟 on 2018/1/4
 */
public class EhCacheRegionFactory extends SingletonEhcacheRegionFactory {
    private static final AtomicInteger REFERENCE_COUNT = new AtomicInteger();
    private Log log = LoggerFactory.createLogger("ehcahce",EhCacheRegionFactory.class);

    @Override
    protected CacheManager resolveCacheManager(SessionFactoryOptions settings, Map properties) {
        try {
            if(log.isInfoEnabled()){
                log.info("初始化hibernate二级缓存区域");
            }
            REFERENCE_COUNT.incrementAndGet();
            return CacheManager.create(EhCacheConfig.configuration());
        }catch (Exception e){
            if(log.isInfoEnabled()){
                log.info("初始化hibernate二级缓存区域失败");
            }
            REFERENCE_COUNT.decrementAndGet();
            throw e;
        }
    }
}
