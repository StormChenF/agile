package com.agile.common.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by 佟盟 on 2017/9/26
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement(proxyTargetClass = true)
@EnableAsync
@EnableCaching
@EnableScheduling
@EnableWebMvc

@ImportResource(value = "classpath:com/agile/configure/spring-container.xml")
@PropertySources({
//        @PropertySource("classpath:com/agile/configure/agile-core.properties",ignoreResourceNotFound = true,encoding = "UTF-8"),
        @PropertySource(value = "classpath:com/agile/configure/agile.properties",ignoreResourceNotFound = true,encoding = "UTF-8")
})

@ComponentScans({@ComponentScan(basePackages = {"com.agile"})})
public class SpringConfig {

}
