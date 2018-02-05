package com.agile.common.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by 佟盟 on 2017/9/26
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
@EnableCaching
@EnableScheduling
@ComponentScan(basePackages = {"com.agile"})
@PropertySources({
//        @PropertySource("classpath:com/agile/configure/agile-core.properties",ignoreResourceNotFound = true,encoding = "UTF-8"),
        @PropertySource(value = "classpath:com/agile/configure/agile.properties",ignoreResourceNotFound = true,encoding = "UTF-8")
})
public class SpringConfig {

}
