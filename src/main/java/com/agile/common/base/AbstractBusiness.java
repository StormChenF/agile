package com.agile.common.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.ParameterExpression;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by tongmeng on 2017/1/9.
 */
public class AbstractBusiness implements InterfaceBusiness {
    //日志工具
    private Logger logger = LogManager.getLogger(this.getClass());
    //输入
    private HashMap<String, Object> inParam = new LinkedHashMap<String, Object>();
    //输出
    private HashMap<String, Object> outParam = new LinkedHashMap<String, Object>();

    /**
     * 根据对象及方法名通过反射执行该对象的指定方法
     * @param methodName    服务内部的具体方法名
     * @return  返回执行结果
     * @throws Exception
     */
    @Transactional("transationManager")
    public RETURN excuteMethod(String methodName) throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException,NoSuchMethodException, SecurityException {
        Method method = this.getClass().getDeclaredMethod(methodName);
        return (RETURN) method.invoke(this);
    }

    public final void setInParam(HashMap<String, Object> inParam) {
        this.inParam = inParam;
    }

    public HashMap<String, Object> getOutParam() {
        return this.outParam;
    }

    public void setOutParam(HashMap<String, Object> outParam) {
        this.outParam = outParam;
    }

}
