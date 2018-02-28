package com.agile.common.cache.redis;

import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;

/**
 * Created by 佟盟 on 2018/2/9
 */
public interface RedisAccessStrategyFactory {
    EntityRegionAccessStrategy createEntityRegionAccessStrategy(RedisEntityRegion var1, AccessType var2);

    CollectionRegionAccessStrategy createCollectionRegionAccessStrategy(RedisCollectionRegion var1, AccessType var2);

    NaturalIdRegionAccessStrategy createNaturalIdRegionAccessStrategy(RedisNaturalIdRegion var1, AccessType var2);
}
