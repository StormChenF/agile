package com.agile.common.annotation;

import com.agile.common.util.ArrayUtil;
import com.agile.common.util.ObjectUtil;
import com.agile.common.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佟盟 on 2018/1/5
 */
@Component
public class AnnotationProcessor implements EnvironmentAware {
    private Environment env;
    /**
     * 准备处理的注解
     */
    public static Class[] classAnnotations = {Properties.class};
    public static Class[] methodAnnotations = {Init.class,TaskTarget.class};

    void Init(Init init, Object bean){
        Method[] methods = AopUtils.getTargetClass(bean).getMethods();
        for (int j = 0;j<methods.length;j++){
            Method method = methods[j];
            method.setAccessible(true);
            Init initAnnotation = method.getAnnotation(Init.class);
            if(!ObjectUtil.isEmpty(initAnnotation)){
                try {
                    method.invoke(bean);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void Properties(Properties properties, Object bean) throws IllegalAccessException, InstantiationException {
        Class<?> targetClass = bean.getClass();
        if(!ObjectUtil.isEmpty(properties)){
            String prefix = properties.prefix();
            Field[] fields = targetClass.getDeclaredFields();
            for(int i = 0 ; i < fields.length;i++){
                Field field = fields[i];
                field.setAccessible(true);
                String name = field.getName();
                Class<?> typeClass = field.getType();
                String propertiesName = prefix+"."+ StringUtil.camelToUnderline(name);
                if(typeClass.isAssignableFrom(List.class)){

                    Type type = field.getGenericType();
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    Type[] typeArguments = parameterizedType.getActualTypeArguments();
                    if(ArrayUtil.isEmpty(typeArguments))continue;
                    Class genericClazz = (Class)typeArguments[0];


                    List list = new ArrayList();
                    int j = 0 ;
                    boolean hasNext = true;
                    if(genericClazz.getPackage().getName().startsWith("java.")){
                        while (hasNext){

                            @SuppressWarnings("unchecked")
                            Object temp = env.getProperty(propertiesName+"["+j+++"]", genericClazz);
                            if(j==0 && ObjectUtil.isEmpty(temp)){
                                temp = env.getProperty(propertiesName, genericClazz);
                            }
                            if(ObjectUtil.isEmpty(temp)){
                                hasNext = false;
                                continue;
                            }
                            list.add(temp);
                        }
                    }else{
                        while (hasNext){
                            Object temp = genericClazz.newInstance();
                            Field[] fieldss = genericClazz.getDeclaredFields();

                            int countNull = 0;
                            for(int k = 0;k<fieldss.length;k++){
                                Field field2 = fieldss[k];
                                field2.setAccessible(true);
                                @SuppressWarnings("unchecked")
                                Object value = env.getProperty(propertiesName+"."+StringUtil.camelToUnderline(field2.getName())+"["+j+"]", field2.getType());
                                if(j==0 && ObjectUtil.isEmpty(value)){
                                    value = env.getProperty(propertiesName+"."+StringUtil.camelToUnderline(field2.getName()), field2.getType());
                                }
                                if(ObjectUtil.isEmpty(value)){
                                    countNull++;
                                    continue;
                                }
                                field2.set(temp,value);
                            }
                            if(countNull==fieldss.length){
                                hasNext = false;
                            }
                            list.add(temp);
                            j++;
                        }
                    }


                    field.set(bean,list);
                }else{
                    Object temp = env.getProperty(propertiesName, typeClass);
                    if(ObjectUtil.isEmpty(temp)){
                        continue;
                    }
                    field.set(bean,temp);
                }
            }
        }
    }

    void TaskTarget(TaskTarget taskTarget, Object bean){

    }

    @Override
    public void setEnvironment(@NotNull Environment environment) {
        this.env = environment;
    }
}
