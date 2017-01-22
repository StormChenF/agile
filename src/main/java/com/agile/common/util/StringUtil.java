package com.agile.common.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tongmeng on 2017/1/9.
 */
public final class StringUtil extends StringUtils{
    /**
     * 特殊符号转驼峰式
     * @param param 任意字符串
     * @return
     */
    public static String signToCamel(String param){
        if (isEmpty(param))return "";

        Matcher testMc = Pattern.compile("[-_*%#$@+=()&^!~`|.,]").matcher(param);
        if(!testMc.find())return param;

        param = param.toLowerCase();
        StringBuffer cacheStr = new StringBuffer(param);
        Matcher mc = Pattern.compile("[-_*%#$@+=()&^!~`|.,]").matcher(param);
        int i = 0;
        while (mc.find()){
            int position=mc.end()-(i++);
            cacheStr.replace(position-1,position+1,cacheStr.substring(position,position+1).toUpperCase());
        }
        return cacheStr.toString();
    }

    /**
     * 字符串转服务类名
     * @param param 任意字符串
     * @return
     */
    public static String toServerName(String param){
        if (isEmpty(param)) return "";
        String service = signToCamel(param);
        return service.substring(0,1).toUpperCase()+service.substring(1);
    }

    /**
     * 字符串转方法名
     * @param param 任意字符串
     * @return
     */
    public static String toMethodName(String param){
        if (isEmpty(param)) return "";
        String method = signToCamel(param);
        return method.substring(0,1).toLowerCase()+method.substring(1);
    }


}
