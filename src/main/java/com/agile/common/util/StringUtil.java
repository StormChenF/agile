package com.agile.common.util;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 佟盟 on 2017/1/9
 */
public final class StringUtil extends StringUtils{
    /**
     * 特殊符号转驼峰式
     * @param param 任意字符串
     * @return 返回驼峰字符串
     */
    public static String signToCamel(String param){
        if (isEmpty(param))return "";

        Matcher testMc = Pattern.compile("[-_*%#$@+=()&^!~`|.,]").matcher(param);
        if(!testMc.find())return param;

        param = param.toLowerCase();
        StringBuilder cacheStr = new StringBuilder(param);
        Matcher mc = Pattern.compile("[-_*%#$@+=()&^!~`|.,]").matcher(param);
        int i = 0;
        while (mc.find()){
            int position=mc.end()-(i++);
            cacheStr.replace(position-1,position+1,cacheStr.substring(position,position+1).toUpperCase());
        }
        return cacheStr.toString();
    }

    /**
     * 字符串转首字母小写驼峰名
     * @param param 任意字符串
     * @return 返回首字母小写的驼峰字符串
     */
    @NotNull
    public static String toName(String param){
        if (isEmpty(param)) return "";
        String camelString = signToCamel(param);
        return camelString.substring(0,1).toUpperCase()+camelString.substring(1);
    }


}
