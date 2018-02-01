package com.agile.common.properties;

import com.agile.common.annotation.Properties;

/**
 * Created by 佟盟 on 2018/2/1
 */
@Properties(prefix = "agile.cache")
public class CacheConfigProperties {
    private String proxy = "ehcache";

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }
}
