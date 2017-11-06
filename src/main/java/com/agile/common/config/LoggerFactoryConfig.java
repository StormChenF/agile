package com.agile.common.config;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.config.plugins.Plugin;

import java.net.URI;

/**
 * Created by 佟盟 on 2017/11/2
 */
@Plugin(name = "LoggerFactoryConfig", category = ConfigurationFactory.CATEGORY)
@Order(1)
public class LoggerFactoryConfig extends ConfigurationFactory {

    private static Configuration createConfiguration(final String name, ConfigurationBuilder<BuiltConfiguration> builder) {
        builder.setConfigurationName(name);

        //log4j2自身内部日志级别
        builder.setStatusLevel(Level.INFO);

        //总日志过滤级别
        builder.add(builder.newFilter("RegexFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL).addAttribute("regex", "com.agile .*"));

        //控制台日志
        AppenderComponentBuilder consoleConfig = builder.newAppender("Stdout", "CONSOLE").addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
        consoleConfig.add(builder.newLayout("PatternLayout").addAttribute("pattern", " %-d{yyyy-MM-dd HH:mm:ss} [ %p ] [ %c ] %m%n"));
        builder.add(consoleConfig);

        //打开工程自身日志
        builder.add(builder.newLogger("com.agile", Level.DEBUG).add(builder.newAppenderRef("Stdout")).addAttribute("additivity", false));

        builder.add(builder.newRootLogger(Level.OFF).add(builder.newAppenderRef("Stdout")));
        return builder.build();

    }

    @Override
    protected String[] getSupportedTypes() {
        return new String[] {"*"};
    }

    @Override
    public Configuration getConfiguration(LoggerContext loggerContext, ConfigurationSource configurationSource) {
        return getConfiguration(loggerContext, configurationSource.toString(), null);
    }

    @Override
    public Configuration getConfiguration(LoggerContext loggerContext, String name, URI configLocation) {
        ConfigurationBuilder<BuiltConfiguration> builder = newConfigurationBuilder();
        return createConfiguration(name, builder);
    }
}
