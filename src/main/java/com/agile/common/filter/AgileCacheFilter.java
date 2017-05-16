package com.agile.common.filter;

import com.agile.common.util.BooleanUtil;
import com.agile.common.util.CacheUtil;
import com.agile.common.util.ObjectUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by 佟盟
 */
@Component
@Aspect
public class AgileCacheFilter {

    @Pointcut(value = "execution(@org.springframework.scheduling.annotation.Scheduled * *(..))")
    public void pointCut(){}

    @Around(value = "pointCut()")
    public Object aroundCache(ProceedingJoinPoint proceedingJoinPoint){
        String cacheKey = proceedingJoinPoint.getSignature().toString();
        if(ObjectUtil.isEmpty(CacheUtil.getCache(cacheKey)) || !BooleanUtil.toBoolean(CacheUtil.getCache(cacheKey))){
            CacheUtil.setCache(cacheKey,true,5);
            try {
                return proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }

}
