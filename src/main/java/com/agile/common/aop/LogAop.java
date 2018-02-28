package com.agile.common.aop;

import com.agile.common.util.ServletUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by 佟盟 on 2017/9/24
 * 处理服务调用日志部分
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    LogProcessor logProcessor;

    @Autowired
    HttpServletRequest request;

    /**
     * 服务切面
     */
    @Pointcut(value = "execution(* com.agile.common.service.ServiceInterface.executeMethod(..))")
    public void servicePointCut() {
    }

    @After(value = "servicePointCut()")
    public void logAround(JoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        Class<?> mainService = target.getClass().getSuperclass();
        @SuppressWarnings("unchecked")
        HashMap<String,Object> inParam = (HashMap<String,Object>) mainService.getDeclaredMethod("getInParam").invoke(target);
        @SuppressWarnings("unchecked")
        HashMap<String,Object> outParam = (HashMap<String,Object>) mainService.getDeclaredMethod("getOutParam").invoke(target);
        logProcessor.buildLog(target.getClass().getSimpleName(),joinPoint.getArgs()[0].toString(),inParam,outParam,ServletUtil.getCustomerIPAddr(request),request.getRequestURL());

    }


}
