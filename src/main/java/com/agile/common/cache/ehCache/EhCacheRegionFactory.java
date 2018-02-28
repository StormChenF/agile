package com.agile.common.cache.ehCache;

import com.agile.common.config.EhCacheConfig;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.*;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 佟盟 on 2018/1/4
 */
public class EhCacheRegionFactory extends SingletonEhCacheRegionFactory {
    private static final AtomicInteger REFERENCE_COUNT = new AtomicInteger();

    public void start(SessionFactoryOptions settings, Properties properties) throws CacheException {
        this.settings = settings;

        try {
            Configuration configuration = EhCacheConfig.configuration();
            if (configuration!=null) {

                this.manager = CacheManager.create(configuration);
                REFERENCE_COUNT.incrementAndGet();
            } else {
                this.manager = CacheManager.create();
                REFERENCE_COUNT.incrementAndGet();
            }

            this.mbeanRegistrationHelper.registerMBean(this.manager, properties);
        } catch (net.sf.ehcache.CacheException var7) {
            throw new CacheException(var7);
        }
    }
}
