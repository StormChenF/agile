package com.agile.common.listener;

import com.agile.common.util.CacheUtil;
import com.agile.common.util.FactoryUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by 佟盟 on 2017/9/7
 */
@WebListener
public class CacheListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //获取上下文信息
        ApplicationContext context =  WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        //获取数据
        //存储数据
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}