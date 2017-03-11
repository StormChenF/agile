package com.agile.common.util;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by mydeathtrial on 2017/3/11
 */
@Component
public class PropertiesUtil {

    private Properties properties;

    private static PropertiesUtil propertiesUtil;

    private PropertiesUtil() {
        try {
            this.properties = new Properties();
            InputStream in = new BufferedInputStream(new FileInputStream("D:\\workspace\\agile\\src\\main\\resources\\com\\agile\\configure\\agile.properties"));
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static PropertiesUtil getObject(){
        if(ObjectUtil.isEmpty(PropertiesUtil.propertiesUtil)){
            PropertiesUtil.propertiesUtil = new PropertiesUtil();
        }
        return PropertiesUtil.propertiesUtil;
    }

    public static String getProperties(String key){
        PropertiesUtil propertiesUtil = PropertiesUtil.getObject();
        return propertiesUtil.properties.getProperty(key);
    }
}
