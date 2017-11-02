package com.agile.common.config;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.slf4j.Logger;

/**
 *
 */
public class LoggerFactory {
    private LoggerFactory() {}
    public static void start(String jobId) {

        //为false时，返回多个LoggerContext对象，   true：返回唯一的单例LoggerContext
        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();

        //创建一个展示的样式：PatternLayout，   还有其他的日志打印样式。
        Layout layout = PatternLayout.createLayout(PatternLayout.DEFAULT_CONVERSION_PATTERN, null,config, null, null, true, false, null, null);
        // TriggeringPolicy tp = SizeBasedTriggeringPolicy.createPolicy("10MB");
        // Appender appender = RollingFileAppender.createAppender(String.format("
        // logs/test/syncshows-job-%s.log", jobID),
        //       "/logs/test/" + jobID + "/syncshows-job-" + jobID + ".log.gz",
        //       "true", jobID, null, null, null, tp, null, layout, null,
        //       null, null, null, config);
        //  日志打印方式——输出为文件

        Appender appender = FileAppender.createAppender(
                String.format("logs/test/syncshows-job-%s.log", jobId), "true", "false",
                "" + jobId, null, "true", "true", null, layout, null, null, null, config);
        appender.start();
        config.addAppender(appender);
        AppenderRef ref = AppenderRef.createAppenderRef("" + jobId, null, null);
        AppenderRef[] refs = new AppenderRef[] { ref };
        LoggerConfig loggerConfig = LoggerConfig.createLogger("false", Level.ALL, "" + jobId,
                "true", refs, null, config, null);
        loggerConfig.addAppender(appender, null, null);
        config.addLogger("" + jobId, loggerConfig);
        ctx.updateLoggers();
    }
    public static void stop(String jobId) {
        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();
        config.getAppender("" + jobId).stop();
        config.getLoggerConfig("" + jobId).removeAppender("" + jobId);
        config.removeLogger("" + jobId);
        ctx.updateLoggers();
    }
    /**
     * 获取Logger
     *
     * 如果不想使用slf4j,那这里改成直接返回Log4j的Logger即可
     * @param jobId
     * @return
     */
    public static Logger createLogger(String jobId) {
        start(jobId);
        return org.slf4j.LoggerFactory.getLogger(jobId);
    }

    public static void main(String[] args) {
        System.setProperty("log4j2.debug","true");

        Logger logger = LoggerFactory.createLogger("com.alibaba.druid.support.logging.SLF4JImpl");
        logger.info("Testing testing testing 111");
        logger.debug("Testing testing testing 222");
        logger.error("Testing testing testing 333");
        LoggerFactory.stop("com.alibaba.druid.support.logging.SLF4JImpl");
//        for (int i = 0; i < 10; i++) {
//            Logger logger = LogConfig.createLogger(i+"");
//            logger.info("Testing testing testing 111");
//            logger.debug("Testing testing testing 222");
//            logger.error("Testing testing testing 333");
//            LogConfig.stop(i+"");
//        }
    }
}
