package com.agile.common.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * Created by 佟盟 on 2017/10/7
 */
@Configuration
public class KaptchaConfig implements EnvironmentAware {
    private Environment env;

    @Bean
    DefaultKaptcha defaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config());
        return defaultKaptcha;
    }

    private Config config(){
        return new Config(properties());
    }

    private Properties properties(){
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border",env.getProperty("kaptcha.border"));
        properties.setProperty("kaptcha.border.color",env.getProperty("kaptcha.border.color"));
        properties.setProperty("kaptcha.textproducer.font.color",env.getProperty("kaptcha.textproducer.font.color"));
        properties.setProperty("kaptcha.textproducer.font.size",env.getProperty("kaptcha.textproducer.font.size"));
        properties.setProperty("kaptcha.image.width",env.getProperty("kaptcha.image.width"));
        properties.setProperty("kaptcha.image.height",env.getProperty("kaptcha.image.height"));
        properties.setProperty("kaptcha.textproducer.char.length",env.getProperty("kaptcha.textproducer.char.length"));
        properties.setProperty("kaptcha.textproducer.font.names",env.getProperty("kaptcha.textproducer.font.names"));
        return properties;
    }

    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }
}
