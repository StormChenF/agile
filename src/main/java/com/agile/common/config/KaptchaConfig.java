package com.agile.common.config;

import com.agile.common.properties.KaptchaConfigProperties;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by 佟盟 on 2017/10/7
 */
@Configuration
public class KaptchaConfig {
    private final KaptchaConfigProperties kaptchaConfigProperties;

    @Autowired
    public KaptchaConfig(KaptchaConfigProperties kaptchaConfigProperties) {
        this.kaptchaConfigProperties = kaptchaConfigProperties;
    }

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
        properties.setProperty("kaptcha.border",kaptchaConfigProperties.getBorder());
        properties.setProperty("kaptcha.border.color",kaptchaConfigProperties.getBorderColor());
        properties.setProperty("kaptcha.textproducer.font.color",kaptchaConfigProperties.getTextproducerFontColor());
        properties.setProperty("kaptcha.textproducer.font.size",kaptchaConfigProperties.getTextproducerFontSize());
        properties.setProperty("kaptcha.image.width",kaptchaConfigProperties.getImageWidth());
        properties.setProperty("kaptcha.image.height",kaptchaConfigProperties.getImageHeight());
        properties.setProperty("kaptcha.textproducer.char.length",kaptchaConfigProperties.getTextproducerCharLength());
        properties.setProperty("kaptcha.textproducer.font.names",kaptchaConfigProperties.getTextproducerFontNames());
        return properties;
    }
}
