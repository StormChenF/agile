package com.agile.common.servlet;

import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Created by 佟盟 on 2017/9/25
 */
@WebServlet(name = "SpringDispatcherServlet",urlPatterns = "/*",loadOnStartup = 1,initParams = {
        @WebInitParam(name = "contextConfigLocation",value = "classpath:com/agile/configure/spring-container.xml;"),@WebInitParam(name = "dispatchOptionsRequest",value = "true")
})
public class SpringDispatcherServlet extends DispatcherServlet {
}
