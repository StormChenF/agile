package com.agile.common.config;

import com.agile.common.annotation.Init;
import com.agile.common.util.ObjectUtil;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 佟盟 on 2017/11/2
 */
//@Component
public class InitConfig implements InitializingBean {
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws InvocationTargetException, IllegalAccessException {
        String[] beanNames = applicationContext.getBeanDefinitionNames();

        for (int i = 0;i<beanNames.length;i++){
            Object bean = applicationContext.getBean(beanNames[i]);

            //获取spring代理的java对象
            Method[] methods = AopUtils.getTargetClass(bean).getMethods();
            for (int j = 0;j<methods.length;j++){
                Method method = methods[j];
                method.setAccessible(true);
                Init initAnnotation = method.getAnnotation(Init.class);
                if(!ObjectUtil.isEmpty(initAnnotation)){
                    method.invoke(bean);
                }
            }
        }
    }
}
