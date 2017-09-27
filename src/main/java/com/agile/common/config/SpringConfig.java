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
@ComponentScan(value = "com.agile")
@ImportResource(value = "classpath:com/agile/configure/spring-container.xml")
public class SpringConfig {

}
