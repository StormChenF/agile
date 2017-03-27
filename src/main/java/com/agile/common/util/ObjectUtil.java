package com.agile.common.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.ObjectUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 佟盟 on 2017/1/9
 */
public class ObjectUtil extends ObjectUtils {
    /**
     * 对象属性拷贝
     * @param source 源对象
     * @param target 目标对象
     * @param useConverter 是否使用转换器
     * @return 转换结果
     */
    public static BeanCopier copyProperties(Class source, Class target, boolean useConverter){
        return BeanCopier.create(source, target, useConverter);
    }

    /**
     * 比较两个对象是否继承与同一个类
     * @param source 源对象
     * @param target 目标对象
     * @return 是否相同
     */
    public static Boolean compareClass(Object source,Object target){
        return isEmpty(source)?isEmpty(target):(!isEmpty(target) && source.getClass().equals(target.getClass()));
    }

    /**
     * 比较两个对象属性是否相同
     * @param source 源对象
     * @param target 目标对象
     * @return 是否相同
     */
    public static boolean compareValue(Object source, Object target) {
        return isEmpty(source)?isEmpty(target):(source.equals(target));
    }

    /**
     * 获取两个对象的不同属性列表
     * @param source 源对象
     * @param target 目标对象
     * @return 值不相同的属性列表
     * @throws IllegalAccessException 调用过程异常
     * @throws InvocationTargetException 调用目标异常
     */
    public static List<Map<String,Object>> getDifferenceProperties(Object source,Object target) throws IllegalAccessException,InvocationTargetException {
        if(((compareClass(source, target) && !compareValue(source, target)) || isEmpty(source)) != isEmpty(target)){
            List<Map<String,Object>> rList = new ArrayList<>();
            Object object = isEmpty(source)?target:source;
            Class sourceClass = object.getClass();
            Method[] methods = sourceClass.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (!methodName.startsWith("get")) {
                    continue;
                }
                Object sourceValue = isEmpty(source)?null:method.invoke(source);
                Object targetValue = isEmpty(target)?null:method.invoke(target);
                Object objectValue = isEmpty(sourceValue)?targetValue:sourceValue;

                if (compareValue(sourceValue,targetValue)) {
                    continue;
                }
                rList.add(new HashMap<String,Object>() {{
                    put("propertyName", methodName.replace("get", ""));
                    put("propertyType",objectValue.getClass().getName());
                    put("oldValue", sourceValue);
                    put("newValue", targetValue);
                }});
            }
            return rList;
        }
        return null;
    }
}
