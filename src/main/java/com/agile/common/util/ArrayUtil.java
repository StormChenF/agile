package com.agile.common.util;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 佟盟 on 2017/7/13
 */
public class ArrayUtil extends ArrayUtils {
    /**
     * 数组累加
     */
    public static Object[] addAll(Object[] array1,Object[]... array2){
        Object[] temp = array1;
        for (Object[] array:array2) {
            temp = addAll(temp,array);
        }
        return temp;
    }

    /**
     * 数组转list
     * @param array 数组
     */
    public static <T>List<T> asList(T... array){
        return Arrays.asList(array);
    }
}
