package com.agile.common.util;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by mydeathtrial on 2017/3/11
 */
@Component
public class PropertiesUtil {

    @Resource
    private Environment environment;

    private Properties properties;

    private static PropertiesUtil propertiesUtil;

    /**
     * 初始化前
     */
    @PostConstruct
    private void init() {
        propertiesUtil = this;
    }

    public PropertiesUtil() {}

    /**
     * 获取工程配置信息
     * @param key 句柄
     * @return 值
     */
    public static String getProperty(String key){
        return propertiesUtil.environment.getProperty(key);
    }

    public static String getProperty(String var1, String var2){
        return propertiesUtil.environment.getProperty(var1, var2);
    }

    public static <T> T getProperty(String var1, Class<T> var2){
        return propertiesUtil.environment.getProperty(var1, var2);
    }

    public static <T> T getProperty(String var1, Class<T> var2, T var3){
        return propertiesUtil.environment.getProperty(var1, var2,var3);
    }

    public static String getRequiredProperty(String var1){
        return propertiesUtil.environment.getRequiredProperty(var1);
    }

    public static <T> T getRequiredProperty(String var1, Class<T> var2){
        return propertiesUtil.environment.getRequiredProperty(var1,var2);
    }

    public static String resolvePlaceholders(String var1){
        return propertiesUtil.environment.resolvePlaceholders(var1);
    }

    public static String resolveRequiredPlaceholders(String var1){
        return propertiesUtil.environment.resolveRequiredPlaceholders(var1);
    }

    public static boolean containsProperty(String var1){
        return propertiesUtil.environment.containsProperty(var1);
    }
    /**********************************************非静态*********************************************
     * 构造函数，读取相对路径下的参数文件
     */
    public PropertiesUtil(String url) {
        try {
            this.properties = new Properties();
            InputStream in = new BufferedInputStream(new FileInputStream(url));
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据key获取参数文件当中value值
     * @param key key值
     * @return value值
     */
    public String getPropertyOfNoStatic(String key){
        return this.properties.getProperty(key);
    }
}
