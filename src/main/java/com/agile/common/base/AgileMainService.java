package com.agile.common.base;

import com.agile.common.util.ObjectUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by 佟盟 on 2017/1/9
 */
public abstract class AgileMainService extends AgileExceptionHandler implements AgileServiceInterface {
    //日志工具
    private Logger logger = LogManager.getLogger(this.getClass());
    //输入
    private HashMap<String, Object> inParam = new LinkedHashMap<>();
    //输出
    private HashMap<String, Object> outParam = new LinkedHashMap<>();

    /**
     * 根据对象及方法名通过反射执行该对象的指定方法
     * @param methodName 服务内部的具体方法名
     * @return 返回执行结果
     */
    public RETURN executeMethod(String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Method method = this.getClass().getDeclaredMethod(methodName);
            return execute(method);
    }
    @Transactional
    private RETURN execute(Method method) throws IllegalAccessException,IllegalArgumentException,InvocationTargetException,SecurityException{
            return (RETURN) method.invoke(this);
    }
    /**
     * 控制层中调用该方法设置服务入参
     * @param inParam 参数集
     */
    public final void setInParam(HashMap<String, Object> inParam) {
        this.inParam = inParam;
    }

    /**
     * 服务中调用该方法获取入参
     * @param key 入参索引字符串
     * @return 入参值
     */
    protected Object getInParam(String key) {
        return inParam.get(key);
    }

    /**
     * 控制层中调用该方法获取响应参数
     * @return 响应参数集
     */
    public HashMap<String, Object> getOutParam() {
        return this.outParam;
    }

    /**
     * 服务中调用该方法设置响应参数
     * @param key 参数索引字符串
     * @param value 参数值
     */
    protected void setOutParam(String key, Object value) {
        if(ObjectUtil.isEmpty(this.outParam)){
            this.outParam = new HashMap<>();
        }
        this.outParam.put(key,value);
    }
}
