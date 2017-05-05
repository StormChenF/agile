package com.agile.common.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.jndi.JndiTemplate;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
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
            for (int i = 0 ; i < methods.length ; i++) {
                Method method = methods[i];
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
                rList.add(new HashMap<String,Object>() {
                    private static final long serialVersionUID = -3959176970036247143L;
                    {
                    put("propertyName", methodName.replace("get", ""));
                    put("propertyType", objectValue != null ? objectValue.getClass().getName() : null);
                    put("oldValue", sourceValue);
                    put("newValue", targetValue);}
                });
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
     */
    public static  <T> T  getObjectFromMap(Class<T> clazz,HashMap<String, Object> map){
        return getObjectFromMap(clazz,map,"","");
    }

    /**
     * 从Map对象中获取指定类型对象
     * @param clazz 想要获取的对象类型
     * @param map 属性集合
     * @return 返回指定对象类型对象
     */
    public static  <T> T  getObjectFromMap(Class<T> clazz,HashMap<String, Object> map, String prefix){
        return getObjectFromMap(clazz,map,prefix,"");
    }

    /**
     * 从Map对象中获取指定类型对象
     * @param clazz 想要获取的对象类型
     * @param map 属性集合
     * @param prefix 属性前缀
     * @return 返回指定对象类型对象
     */
    public static <T> T getObjectFromMap(Class<T> clazz,HashMap<String, Object> map, String prefix, String suffix) {
        Object object = null;
        try {
            object = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0 ; i < fields.length ; i++) {
            Field field = fields[i];
            String propertyKey = prefix + field.getName() + suffix;
            if(map.containsKey(propertyKey)){
                Class<?> propertyType = field.getType();
                String propertyValue = map.get(propertyKey).toString();
                try {
                    Method setMethod = clazz.getDeclaredMethod("set" + StringUtil.toUpperName(propertyKey),propertyType);
                    setMethod.invoke(object,propertyValue);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        return (T) object;
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
