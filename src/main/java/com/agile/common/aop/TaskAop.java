package com.agile.common.aop;

import com.agile.common.util.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * Created by 佟盟
 */
@Component
@Aspect
public class TaskAop {

    //切面
    @Pointcut(value = "execution(@org.springframework.scheduling.annotation.Scheduled * *(..))")
    public void taskPointCut() {
    }

    //环绕通知
    @Around(value = "taskPointCut()")
    public Object aroundCache(ProceedingJoinPoint proceedingJoinPoint) {
        Signature signature = proceedingJoinPoint.getSignature();
        String cacheKey = signature.toString();
        Annotation annotation = null;
        String splitSpot = "\\.", splitNbsp = " ", splitSlash = "/", target = "()", replacement = "";
        int timeToldleSeconds = 0;
        try {
            String temporaryStr = cacheKey.replace(target, replacement);
            String[] temporaryStrs = temporaryStr.split(splitSpot);
            int max = temporaryStrs.length;
            annotation = signature.getDeclaringType().getMethod(temporaryStrs[max - 1]).getAnnotation(Scheduled.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        String cron = annotation != null ? ((Scheduled) annotation).cron() : null;
        if (StringUtil.isEmpty(cron)) return null;

        String[] temporaryStrs = cron.split(splitNbsp);
        for (int i = 0; i < temporaryStrs.length; i++) {
            if (temporaryStrs[i].contains(splitSlash)) {
                String[] times = temporaryStrs[i].split(splitSlash);
                timeToldleSeconds = (i == 0) ? Integer.parseInt(times[1]) : Integer.parseInt(times[1]) * i * 60;
                break;
            } else {
                timeToldleSeconds = 60 * 60;
            }
        }
        if (ObjectUtil.isEmpty(CacheUtil.getCache(cacheKey)) || !BooleanUtil.toBoolean(String.valueOf(CacheUtil.getCache(cacheKey)))) {
            CacheUtil.setCache(cacheKey, true, timeToldleSeconds);
            try {
                return proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }

}
