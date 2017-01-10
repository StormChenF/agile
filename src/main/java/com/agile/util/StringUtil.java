package com.agile.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tongmeng on 2017/1/9.
 */
public final class StringUtil extends StringUtils{

    public static String lineToCamel(String param){
        if (isEmpty(param)){
            return "";
        }else{
            param = param.toLowerCase();
        }
        StringBuffer cacheStr = new StringBuffer(param);
        cacheStr.replace(0,1,cacheStr.substring(0,1).toUpperCase());
        Matcher mc = Pattern.compile("[-_*%]").matcher(param);
        int i = 0;
        while (mc.find()){
            int position=mc.end()-(i++);
            cacheStr.replace(position-1,position+1,cacheStr.substring(position,position+1).toUpperCase());
        }
        return cacheStr.toString();
    }

    public static String urlToMethod(String param){
        String method = lineToCamel(param);
        if (isEmpty(method)){
            return "";
        }else{
            return method.substring(0,1).toLowerCase()+method.substring(1);
        }
    }
}
