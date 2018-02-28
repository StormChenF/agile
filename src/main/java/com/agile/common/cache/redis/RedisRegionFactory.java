package com.agile.common.cache.redis;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class RedisRegionFactory extends AbstractRedisRegionFactory {
    private static final AtomicInteger REFERENCE_COUNT = new AtomicInteger();

    public synchronized void start(SessionFactoryOptions settings, Properties properties) throws CacheException {
        this.settings = settings;

        try {
            this.initializeRegionFactory(settings);
            REFERENCE_COUNT.incrementAndGet();
        } catch (Exception var4) {
            throw new CacheException(var4);
        }
    }

    public synchronized void stop() {
        if (REFERENCE_COUNT.decrementAndGet() == 0) {
            try {
                this.destroy();
            } catch (Exception var2) {
                throw new CacheException(var2);
            }
        }

    }
}
