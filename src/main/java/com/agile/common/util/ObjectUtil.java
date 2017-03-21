package com.agile.common.util;

import com.agile.mvc.model.entity.SysAuthoritiesEntity;
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
        Class<?> a = source.getClass();
        Class<?> b = target.getClass();
        return a.equals(b);
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
        if(compareClass(source, target) && !source.equals(target)){
            List<Map<String,Object>> rList = new ArrayList<>();
            Class sourceClass = source.getClass();
            Method[] methods = sourceClass.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (!methodName.startsWith("get")) {
                    continue;
                }
                Object sourceValue = method.invoke(source);
                Object targetValue = method.invoke(target);
                if (sourceValue.equals(targetValue)) {
                    continue;
                }
                rList.add(new HashMap<String,Object>() {{
                    put("propertyName", methodName.replace("get", ""));
                    put("propertyType",sourceValue.getClass().getName());
                    put("oldValue", sourceValue);
                    put("newValue", targetValue);
                }});
            }
            return rList;
        }
        return null;
    }


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        SysAuthoritiesEntity s = new SysAuthoritiesEntity();
        s.setAuthorityDesc("1111111111");
        s.setAuthorityMark("2222222222");
        s.setAuthorityName("3333333333");
        s.setEnable(true);
        s.setIssys(true);
        s.setMessage("4444444444");
        s.setModuleId("12");
        SysAuthoritiesEntity s1 = new SysAuthoritiesEntity();
        s1.setAuthorityDesc("1111111112");
        s1.setAuthorityMark("2222222223");
        s1.setAuthorityName("3333333333");
        s1.setEnable(true);
        s1.setIssys(true);
        s1.setMessage("4444444444");
        s1.setModuleId("12");
        List<Map<String, Object>> d = getDifferenceProperties(s, s1);
    }
}
