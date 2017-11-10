package com.agile.common.config;

import com.agile.common.util.ObjectUtil;
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
import org.slf4j.LoggerFactory;
/**
 *
 */
public class LogConfig {
    private static LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
    private static Configuration config = ctx.getConfiguration();
    private LogConfig() {}
    public static void start(String fileName) {

        //创建一个展示的样式：PatternLayout，   还有其他的日志打印样式。
        Layout layout = PatternLayout.newBuilder().withConfiguration(config).withPattern("%-d{yyyy-MM-dd HH:mm:ss} [ %p ] [ %c ] %m%n").build();

        if(ObjectUtil.isEmpty(config.getAppender(fileName))){
            //输出引擎
            @SuppressWarnings("unchecked")
            Appender appender = FileAppender.newBuilder().withName(fileName).withFileName(String.format("logs/%s.log", fileName)).withAppend(true).withLocking(false).withIgnoreExceptions(true).withBufferedIo(true).withLayout(layout).build();
            appender.start();
            config.addAppender(appender);
        }

        AppenderRef ref = AppenderRef.createAppenderRef(fileName, null, null);
        AppenderRef[] refs = new AppenderRef[] { ref };

        if(!config.getLoggers().containsKey(fileName)){
            LoggerConfig loggerConfig = LoggerConfig.createLogger(false, Level.ALL,  fileName,"true", refs, null, config, null);
            loggerConfig.addAppender(config.getAppender(fileName), null, null);
            config.addLogger(fileName,loggerConfig);
        }
        ctx.updateLoggers();
    }
    public static void stop(String jobId) {
        config.getAppender("" + jobId).stop();
        config.getLoggerConfig("" + jobId).removeAppender("" + jobId);
        config.removeLogger("" + jobId);
        ctx.updateLoggers();
    }
    /**
     * 获取Logger
     *
     * 如果不想使用slf4j,那这里改成直接返回Log4j的Logger即可
     * @return
     */
    public static Logger createLogger(String fileName) {
        start(fileName);
        return LoggerFactory.getLogger(LogConfig.class);
    }

    public static void main(String[] args) {
            Logger logger = LogConfig.createLogger("test1");
            logger.info("Testing testing testing 111");
            logger.debug("Testing testing testing 222");
            logger.error("Testing testing testing 333");
            LogConfig.stop("test1");
    }
}
