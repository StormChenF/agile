package com.agile.common.config;

import com.agile.common.annotation.AnnotationProcessor;
import com.agile.common.properties.KaptchaConfigProperties;
import com.agile.common.properties.SpringMVCProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by 佟盟 on 2018/1/19
 */
@Component
public class BeanDefinitionRegistryPostProcessor implements org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor,EnvironmentAware,ApplicationContextAware {
    private Environment environment;
    private ApplicationContext applicationContext;
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        this.cacheManagerProcessor(beanDefinitionRegistry);
        this.annotationProcessor();
    }
    
    /**
     * 解决多个cacheManager导致spring无法成功获取缓存控制器问题
     */
    private void cacheManagerProcessor(BeanDefinitionRegistry beanDefinitionRegistry){
        String cacheProxy = environment.getProperty("agile.cache.proxy").toLowerCase();
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
        beanDefinitionRegistry.getBeanDefinition(cache).setPrimary(true);
    }

    /**
     * 处理自定义注解
     */
    private void annotationProcessor(){
        for(int i = 0; i < AnnotationProcessor.classAnnotations.length; i++){
            this.annotationPricessor(AnnotationProcessor.classAnnotations[i]);
        }
    }

    private <T extends Annotation> void annotationPricessor(Class<T> clazz){
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(clazz);
        for(Map.Entry<String, Object> map: beans.entrySet()){
            Object bean = map.getValue();
            Class<?> targetClass = bean.getClass();
            if(targetClass == SpringMVCProperties.class || targetClass == KaptchaConfigProperties.class){
                System.out.println();
            }
            T properties = targetClass.getAnnotation(clazz);
            try {
                Method method = AnnotationProcessor.class.getDeclaredMethod(clazz.getSimpleName(), clazz, Object.class);
                method.setAccessible(true);
                ReflectionUtils.invokeMethod(method,applicationContext.getBean(AnnotationProcessor.class),properties,bean);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
