package com.agile.common.cache.redis;

import org.hibernate.cache.redis.strategy.*;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 佟盟 on 2018/2/9
 */
public class RedisAccessStrategyFactoryImpl implements RedisAccessStrategyFactory {
    private static final Logger log = LoggerFactory.getLogger(RedisAccessStrategyFactoryImpl.class);

    public RedisAccessStrategyFactoryImpl() {
    }

    public EntityRegionAccessStrategy createEntityRegionAccessStrategy(RedisEntityRegion entityRegion, AccessType accessType) {
        switch(accessType) {
            case READ_ONLY:
                if (entityRegion.getCacheDataDescription().isMutable()) {
                    log.warn("read-only cache configured for mutable entity regionName=[{}]", entityRegion.getName());
                }

                return new ReadOnlyRedisEntityRegionAccessStrategy(entityRegion, entityRegion.getSettings());
            case READ_WRITE:
                return new ReadWriteRedisEntityRegionAccessStrategy(entityRegion, entityRegion.getSettings());
            case NONSTRICT_READ_WRITE:
                return new NonStrictReadWriteRedisEntityRegionAccessStrategy(entityRegion, entityRegion.getSettings());
            case TRANSACTIONAL:
                return new TransactionalRedisEntityRegionAccessStrategy(entityRegion, entityRegion.getSettings());
            default:
                throw new IllegalArgumentException("unrecognized access strategy type [" + accessType + "]");
        }
    }

    public CollectionRegionAccessStrategy createCollectionRegionAccessStrategy(RedisCollectionRegion collectionRegion, AccessType accessType) {
        switch(accessType) {
            case READ_ONLY:
                if (collectionRegion.getCacheDataDescription().isMutable()) {
                    log.warn("read-only cache configured for mutable entity collectionRegionName=[{}]", collectionRegion.getName());
                }

                return new ReadOnlyRedisCollectionRegionAccessStrategy(collectionRegion, collectionRegion.getSettings());
            case READ_WRITE:
                return new ReadWriteRedisCollectionRegionAccessStrategy(collectionRegion, collectionRegion.getSettings());
            case NONSTRICT_READ_WRITE:
                return new NonStrictReadWriteRedisCollectionRegionAccessStrategy(collectionRegion, collectionRegion.getSettings());
            case TRANSACTIONAL:
                return new TransactionalRedisCollectionRegionAccessStrategy(collectionRegion, collectionRegion.getSettings());
            default:
                throw new IllegalArgumentException("unrecognized access strategy type [" + accessType + "]");
        }
    }

    public NaturalIdRegionAccessStrategy createNaturalIdRegionAccessStrategy(RedisNaturalIdRegion naturalIdRegion, AccessType accessType) {
        switch(accessType) {
            case READ_ONLY:
                if (naturalIdRegion.getCacheDataDescription().isMutable()) {
                    log.warn("read-only cache configured for mutable entity naturalIdRegion=[{}]", naturalIdRegion.getName());
                }

                return new ReadOnlyRedisNaturalIdRegionAccessStrategy(naturalIdRegion, naturalIdRegion.getSettings());
            case READ_WRITE:
                return new ReadWriteRedisNaturalIdRegionAccessStrategy(naturalIdRegion, naturalIdRegion.getSettings());
            case NONSTRICT_READ_WRITE:
                return new NonStrictReadWriteRedisNaturalIdRegionAccessStrategy(naturalIdRegion, naturalIdRegion.getSettings());
            case TRANSACTIONAL:
                return new TransactionalRedisNaturalIdRegionAccessStrategy(naturalIdRegion, naturalIdRegion.getRedis(), naturalIdRegion.getSettings());
            default:
                throw new IllegalArgumentException("unrecognized access strategy type [" + accessType + "]");
        }
    }
}
