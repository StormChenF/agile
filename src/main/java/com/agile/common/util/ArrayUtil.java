package com.agile.common.util;

import org.apache.commons.lang.ArrayUtils;

/**
 * Created by 佟盟 on 2017/7/13
 */
public class ArrayUtil extends ArrayUtils {
    public static Object[] addAll(Object[] array1,Object[]... array2){
        Object[] temp = array1;
        for (Object[] array:array2) {
            temp = addAll(temp,array);
        }
        return temp;
    }
}
