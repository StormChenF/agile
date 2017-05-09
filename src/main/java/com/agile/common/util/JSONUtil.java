package com.agile.common.util;


import com.alibaba.druid.support.json.JSONUtils;
import net.sf.json.JSONObject;

/**
 * Created by mydeathtrial on 2017/5/9
 */
public class JSONUtil extends JSONUtils {
    public static String toJSONString(Object object) {
        return JSONObject.fromObject(object).toString();
    }

    public static JSONObject toJSON(Object object){
        return JSONObject.fromObject(object);
    }
}
