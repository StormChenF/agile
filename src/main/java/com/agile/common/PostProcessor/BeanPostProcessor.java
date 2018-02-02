package com.agile.common.PostProcessor;

import com.agile.common.annotation.AnnotationProcessor;
import com.agile.common.util.ArrayUtil;
import com.agile.common.util.ObjectUtil;
import com.agile.mvc.model.dao.Dao;
import com.agile.mvc.model.entity.SysTaskTargetEntity;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by 佟盟 on 2018/1/19
 */
@Component
public class BeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor,ApplicationContextAware{
    private ApplicationContext applicationContext;
    private final Dao dao;

    @Autowired
    public BeanPostProcessor(Dao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        testSysTask(bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
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

    /**
     * 检测任务
     */
    void testSysTask(Object bean){
        Class<?> clazz = bean.getClass();
        Package packageInfo = clazz.getPackage();
        if(ObjectUtil.isEmpty(packageInfo))return;
        String packageName = packageInfo.getName();
        if(!packageName.startsWith("com.agile.mvc.service"))return;
        Method[] methods =  clazz.getDeclaredMethods();
        for(int i = 0; i < methods.length; i++){
            Method method = methods[i];
            String methodName = method.getName();
            String[] excludeMethod = {"save","delete","update","query"};
            if(ArrayUtil.contains(excludeMethod,methodName))continue;
            String id = clazz.getName() + "." + methodName;
            SysTaskTargetEntity entity = dao.findOne(SysTaskTargetEntity.class, id);
            if(!ObjectUtil.isEmpty(entity))continue;
            SysTaskTargetEntity sysTaskTargetEntity = new SysTaskTargetEntity();
            sysTaskTargetEntity.setSysTaskTargetId(id);
            sysTaskTargetEntity.setTargetPackage(packageName);
            sysTaskTargetEntity.setTargetClass(clazz.getSimpleName());
            sysTaskTargetEntity.setTargetMethod(methodName);
            sysTaskTargetEntity.setName(id);
            dao.update(sysTaskTargetEntity);
        }
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
