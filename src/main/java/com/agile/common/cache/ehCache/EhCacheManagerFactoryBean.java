package com.agile.common.cache.ehCache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;

/**
 * Created by 佟盟 on 2018/2/11
 */
public class EhCacheManagerFactoryBean implements FactoryBean<CacheManager>, InitializingBean, DisposableBean {
    protected final Log logger = LogFactory.getLog(this.getClass());
    @Nullable
    private Configuration configLocation;
    @Nullable
    private String cacheManagerName;
    private boolean acceptExisting = false;
    private boolean shared = false;
    @Nullable
    private CacheManager cacheManager;
    private boolean locallyManaged = true;

    public EhCacheManagerFactoryBean() {
    }

    public void setConfigLocation(Configuration configLocation) {
        this.configLocation = configLocation;
    }

    public void setCacheManagerName(String cacheManagerName) {
        this.cacheManagerName = cacheManagerName;
    }

    public void setAcceptExisting(boolean acceptExisting) {
        this.acceptExisting = acceptExisting;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public void afterPropertiesSet() throws CacheException {
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Initializing EhCache CacheManager" + (this.cacheManagerName != null ? " '" + this.cacheManagerName + "'" : ""));
        }

        Configuration configuration = this.configLocation;
        if (this.cacheManagerName != null) {
            configuration.setName(this.cacheManagerName);
        }

        if (this.shared) {
            this.cacheManager = CacheManager.create(configuration);
        } else if (this.acceptExisting) {
            Class var2 = CacheManager.class;
            synchronized(CacheManager.class) {
                this.cacheManager = CacheManager.getCacheManager(this.cacheManagerName);
                if (this.cacheManager == null) {
                    this.cacheManager = new CacheManager(configuration);
                } else {
                    this.locallyManaged = false;
                }
            }
        } else {
            this.cacheManager = new CacheManager(configuration);
        }

    }

    @Nullable
    public CacheManager getObject() {
        return this.cacheManager;
    }

    public Class<? extends CacheManager> getObjectType() {
        return this.cacheManager != null ? this.cacheManager.getClass() : CacheManager.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void destroy() {
        if (this.cacheManager != null && this.locallyManaged) {
            if (this.logger.isInfoEnabled()) {
                this.logger.info("Shutting down EhCache CacheManager" + (this.cacheManagerName != null ? " '" + this.cacheManagerName + "'" : ""));
            }

            this.cacheManager.shutdown();
        }

    }
}
