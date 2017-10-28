package com.agile.common.listener;


import org.apache.logging.log4j.core.config.Configurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Enumeration;

/**
 * Created by 佟盟 on 2017/10/22
 */
public class Log4jListener implements ServletContextListener {
    private static final String KEY = "log4jConfiguration";

    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0)
    {
        String fileName = getContextParam(arg0);
        Configurator.initialize("Log4j2", fileName);
    }

    @SuppressWarnings("unchecked")
    private String getContextParam(ServletContextEvent event) {
        Enumeration<String> names = event.getServletContext().getInitParameterNames();
        while (names.hasMoreElements())
        {
            String name = names.nextElement();
            String value = event.getServletContext().getInitParameter(name);
            if(name.trim().equals(KEY))
            {
                return value;
            }
        }
        return null;
    }
}
