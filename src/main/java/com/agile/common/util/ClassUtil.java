package com.agile.common.util;

import org.apache.commons.lang.ClassUtils;

/**
 * Created by mydeathtrial on 2017/4/24
 */
public class ClassUtil extends ClassUtils {
    public static String toWrapperNameFromName(String name){
        if ("byte".equals(name))return "Byte";
        if ("short".equals(name))return "Short";
        if ("int".equals(name))return "Integer";
        if ("long".equals(name))return "Long";
        if ("float".equals(name))return "Float";
        if ("double".equals(name))return "Double";
        if ("boolean".equals(name))return "Boolean";
        if ("char".equals(name))return "Character";
        return null;
    }
}
