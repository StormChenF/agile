package com.agile.common.util;

import org.apache.commons.lang.BooleanUtils;

/**
 * Created by mydeathtrial on 2017/4/19
 */
public class BooleanUtil extends BooleanUtils {
    public static boolean toBoolean(String resource){
        return "yes".equals(resource.toLowerCase()) || "true".equals(resource.toLowerCase()) || "1".equals(resource);
    }
}
