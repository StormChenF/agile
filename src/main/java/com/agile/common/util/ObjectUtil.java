package com.agile.common.util;

import com.agile.mvc.model.entity.LogMainEntity;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.*;

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

    /**
     * 从Map对象中获取指定类型对象
     * @param clazz 想要获取的对象类型
     * @param map 属性集合
     * @return 返回指定对象类型对象
     * @throws IllegalAccessException 非法访问
     * @throws InstantiationException 实例化异常
     * @throws NoSuchFieldException 没有指定方法
     */
    public static Object getObjectFromMap(Class<?> clazz,HashMap<String, Object> map) throws IllegalAccessException, InstantiationException, NoSuchFieldException{
        return getObjectFromMap(clazz,map,null);
    }

    /**
     * 从Map对象中获取指定类型对象
     * @param clazz 想要获取的对象类型
     * @param map 属性集合
     * @param prefix 属性前缀
     * @return 返回指定对象类型对象
     * @throws IllegalAccessException 非法访问
     * @throws InstantiationException 实例化异常
     */
    public static Object getObjectFromMap(Class<?> clazz,HashMap<String, Object> map, String prefix) throws IllegalAccessException, InstantiationException {
        Object object = clazz.newInstance();
        for (Map.Entry<String, Object> entry : map.entrySet()){
            if (!isEmpty(entry.getValue())) {
                String fieldName = entry.getKey();
                if(!isEmpty(prefix) && fieldName.startsWith(prefix)){
                    fieldName = fieldName.replaceFirst(prefix, "");
                }

                try {
                    Field field = clazz.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();
                    Object propertyValue = entry.getValue();
                    field.set(object,cast(fieldType,propertyValue));
                }catch (NoSuchFieldException e){
                    continue;
                }

            }
        }
        return object;
    }

    /**
     * 对象类型转换
     * @param clazz 类型
     * @param propertyValue 值
     * @return 转换后的值
     */
    public static Object cast(Class<?> clazz, Object propertyValue) {
        String propertyValueStr = String.valueOf(propertyValue);
        if(clazz == Date.class){
            return Date.valueOf(propertyValueStr);
        }
        if(clazz == Long.class|| clazz == long.class ){
            return Long.parseLong(propertyValueStr);
        }
        if(clazz == Integer.class || clazz == int.class ){
            return Integer.parseInt(propertyValueStr);
        }
        if(clazz == BigDecimal.class){
            return new BigDecimal(propertyValueStr);
        }
        if(clazz == Double.class){
            return Double.parseDouble(propertyValueStr);
        }
        if(clazz == Float.class){
            return Float.parseFloat(propertyValueStr);
        }
        if(clazz == Boolean.class){
            return Boolean.parseBoolean(propertyValueStr);
        }
        return propertyValueStr;
    }
}
