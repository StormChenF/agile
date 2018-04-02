package com.agile.common.util;

import com.agile.common.base.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.validation.BeanPropertyBindingResult;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 佟盟 on 2017/1/9
 */
public final class StringUtil extends StringUtils {
    /**
     * 驼峰式转下划线
     * @param text 任意字符串
     * @return 返回驼峰字符串
     */
    public static String camelToUnderline(String text){
        String regex = Constant.RegularAbout.UPER;
        if(!haveMatchedString(regex, text))return text;

        StringBuilder cacheStr = new StringBuilder(text);
        Matcher matcher = Pattern.compile(regex).matcher(text);
        int i = 0;
        while (matcher.find()){
            int position = matcher.start()+i;
            if(position>=1 && !"_".equals(cacheStr.substring(position-1,position))){
                cacheStr.replace(position,position+1,"_" + cacheStr.substring(position,position+1).toLowerCase());
                i++;
            }
        }
        return cacheStr.toString();
    }

    /**
     * 特殊符号转驼峰式
     * @param text 任意字符串
     * @return 返回驼峰字符串
     */
    public static String signToCamel(String text){
        String regex = Constant.RegularAbout.HUMP;
        if(!haveMatchedString(regex, text))return text;

        StringBuilder cacheStr = new StringBuilder(text);
        Matcher matcher = Pattern.compile(regex).matcher(text);
        int i = 0;
        while (matcher.find()){
            int position=matcher.end()-(i++);
            cacheStr.replace(position-1,position+1,cacheStr.substring(position,position+1).toUpperCase());
        }
        return cacheStr.toString();
    }

    /**
     * 字符串转首字母大写驼峰名
     * @param text 任意字符串
     * @return 返回首字母大写的驼峰字符串
     */
    public static String toUpperName(String text){
        if (isEmpty(text)) return "";
        String camelString = signToCamel(text);
        return camelString.substring(0,1).toUpperCase()+camelString.substring(1);
    }

    /**
     * 字符串转首字母小写驼峰名
     * @param text 任意字符串
     * @return 返回首字母小写的驼峰字符串
     */
    public static String toLowerName(String text){
        if (isEmpty(text)) return "";
        String camelString = signToCamel(text);
        return camelString.substring(0,1).toLowerCase()+camelString.substring(1);
    }

    /**
     * map格式转url参数路径
     * @param map 参数集合
     * @return url参数
     */
    public static String fromMapToUrl(Map<String,Object> map){
        StringBuilder mapOfString = new StringBuilder(Constant.RegularAbout.NULL);
        for (Map.Entry<String, Object> entity : map.entrySet()) {
            Object value = entity.getValue();
            if(value.getClass().isArray()){
                for (Object v:(Object[])value) {
                    mapOfString.append(Constant.RegularAbout.AND).append(entity.getKey());
                    mapOfString.append(Constant.RegularAbout.EQUAL).append(v);
                }
            }else if(!(value instanceof Page) && !(value instanceof BeanPropertyBindingResult)){
                mapOfString.append(Constant.RegularAbout.AND).append(entity.getKey());
                mapOfString.append(Constant.RegularAbout.EQUAL).append(entity.getValue());
            }
        }
        String urlParam = mapOfString.toString();
        return urlParam.startsWith(Constant.RegularAbout.AND)?urlParam.substring(1):urlParam;
    }

    /**
     * 字符串比较
     * @param resource 比较方
     * @param target 参照方
     * @return 是否相同
     */
    public static boolean compare(String resource, String target){
        return ObjectUtil.isEmpty(resource)?ObjectUtil.isEmpty(target):resource.equals(target);
    }

    /**
     * 获取字符串中匹配正则表达式的部分
     * @param regex 正则表达式
     * @param text 正文
     * @return 匹配的字符串
     */
    public static boolean containMatchedString(String regex,String text){
        Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    /**
     * 获取字符串中匹配正则表达式的部分
     * @param regex 正则表达式
     * @param text 正文
     * @return 匹配的字符串
     */
    public static String[] getMatchedString(String regex,String text){
        Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()){
            sb.append(matcher.group()).append(",");
        }
        if(isEmpty(sb.toString())){
            return null;
        }
        return sb.toString().split(",");
    }

    public static String[] getGroupString(String regex,String text){
        Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        int count = matcher.groupCount();
        if (count>0){
            String[] s = new String[count-1];
            if (matcher.find()){
                for (int i = 1 ; i < count;i++)
                s[i-1] = matcher.group(i);
            }
            return s;
        }
        return null;
    }

    /**
     * 获取字符串中匹配正则表达式的部分
     * @param regex 正则表达式
     * @param text 正文
     * @param index 第几组
     * @return 匹配的字符串
     */
    public static String getMatchedString(String regex,String text,int index){
        Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        int i = 0 ;
        while(matcher.find()){
            if(i==index){
                return matcher.group();
            }
            i++;
        }
        return null;
    }

    /**
     * 获取字符串中匹配正则表达式的部分
     * @param regex 正则表达式
     * @param text 正文
     * @return 匹配的字符串
     */
    public static boolean haveMatchedString(String regex,String text){
        Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){
            return true;
        }
        return false;
    }

    /**
     * 判断是否为字符串
     */
    public static boolean isString(Object object){
        return object instanceof String;
    }

    /**
     * 比较长短
     */
    public static boolean compareTo(String resource,String target){
        return resource.length()>target.length();
    }


    /**
     * 字符数组转16进制字符串
     */
    public static String coverToHex(byte[] bytes){
        StringBuilder result = new StringBuilder();
        if(ArrayUtil.isEmpty(bytes))return null;

        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                result.append(0);
            }
            result.append(hv);
        }
        return result.toString().toUpperCase();
    }
}
