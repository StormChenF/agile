package com.agile.common.config;

import com.agile.common.annotation.AnnotationProcessor;
import com.agile.common.util.ArrayUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by 佟盟 on 2018/1/19
 */
@Component
public class BeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor,ApplicationContextAware{
    private ApplicationContext applicationContext;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("dictionaryDataService"))
        annotationProcessor(bean);
        return bean;
    }

    /**
     * 处理自定义注解
     */
    private void annotationProcessor(Object bean) {
        Method[] methods =  ReflectionUtils.getUniqueDeclaredMethods(bean.getClass());
        for(int i = 0; i < methods.length; i++){
            Method method = methods[i];
            Annotation[] annotations = method.getAnnotations();
            for(int j = 0 ; j < annotations.length;j++){
                Class<? extends Annotation> clazz = annotations[j].annotationType();
                if(ArrayUtil.contains(AnnotationProcessor.methodAnnotations,clazz)){
                    try {
                        Method annotationMethod = AnnotationProcessor.class.getDeclaredMethod(clazz.getSimpleName(), clazz, Object.class,Method.class);
                        annotationMethod.setAccessible(true);
                        ReflectionUtils.invokeMethod(annotationMethod,applicationContext.getBean(AnnotationProcessor.class),annotations[j],bean,method);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
