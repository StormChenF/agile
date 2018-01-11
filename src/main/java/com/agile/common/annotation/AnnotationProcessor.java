package com.agile.common.annotation;

import com.agile.common.util.ArrayUtil;
import com.agile.common.util.ObjectUtil;
import com.agile.common.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佟盟 on 2018/1/5
 */
@Component
public class AnnotationProcessor extends ConfigurationClassPostProcessor implements BeanPostProcessor,BeanFactoryPostProcessor, EnvironmentAware {
    private Environment env;
    /**
     * 准备处理的注解
     */
    private Class[] annotations = {Properties.class,TaskTarget.class};

    /**
     * 解决多个cacheManager导致spring无法成功获取缓存控制器问题
     */
    @Override
    public void postProcessBeanFactory(@NotNull ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        String cacheProxy = env.getProperty("agile.cache.proxy").toLowerCase();
        String cache = null;
        switch (cacheProxy){
            case "redis":
                cache = "redisCacheManager";
                break;
            case "ehcache":
                cache = "ehCacheCacheManager";
                break;
        }
        assert cache != null;
        configurableListableBeanFactory.getBeanDefinition(cache).setPrimary(true);
    }

    /**
     * 处理自定义注解
     */
    @NotNull
    @Override
    public Object postProcessBeforeInitialization(@NotNull Object bean, String beanName) throws BeansException {
        Class<?> targetClass = bean.getClass();
        for(int i = 0 ; i < annotations.length;i++){
            Class annotationClass = annotations[i];
            Annotation annotation = AnnotationUtils.getAnnotation(targetClass, annotationClass);
            if(ObjectUtil.isEmpty(annotation))continue;
            try {
                Method method = AnnotationProcessor.class.getDeclaredMethod(annotationClass.getSimpleName(), annotationClass, Object.class, String.class);
                method.setAccessible(true);
                ReflectionUtils.invokeMethod(method,this,annotation,bean,beanName);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return bean;
    }

    void Properties(Properties properties,Object bean, String beanName) throws IllegalAccessException {
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
                    while (hasNext){
                        @SuppressWarnings("unchecked")
                        Object temp = env.getProperty(propertiesName+"["+j+++"]", genericClazz);
                        if(ObjectUtil.isEmpty(temp)){
                            hasNext = false;
                            continue;
                        }
                        list.add(temp);
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

    void TaskTarget(TaskTarget taskTarget,Object bean, String beanName){

    }

    @Override
    public void setEnvironment(@NotNull Environment environment) {
        this.env = environment;
    }
}
