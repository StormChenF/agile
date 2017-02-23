package com.agile.common.base;

import org.hibernate.HibernateException;
import org.springframework.beans.BeansException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.ParameterExpression;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.DateTimeException;
import java.util.concurrent.TimeoutException;

/**
 * Created by tongmeng on 2017/2/23.
 */
public class AgileExceptionHandler implements HandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();//响应视图对象
        if(e instanceof ParameterExpression){
            modelAndView.addObject("head",new HEAD(RETURN.PARAMETER_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof SQLException){
            modelAndView.addObject("head",new HEAD(RETURN.SQL_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof HibernateException){
            modelAndView.addObject("head",new HEAD(RETURN.HIBERNATE_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof DateTimeException){
            modelAndView.addObject("head",new HEAD(RETURN.DATETIME_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof NullPointerException){
            modelAndView.addObject("head",new HEAD(RETURN.NULL_POINTER_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof ParseException){
            modelAndView.addObject("head",new HEAD(RETURN.PARSE_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof IOException){
            modelAndView.addObject("head",new HEAD(RETURN.IO_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof TimeoutException){
            modelAndView.addObject("head",new HEAD(RETURN.TIMEOUT_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof IllegalAccessException){
            modelAndView.addObject("head",new HEAD(RETURN.IIIEGAL_ACCESS_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof IllegalArgumentException){
            modelAndView.addObject("head",new HEAD(RETURN.IIIEGAL_ARGUMENT_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof InvocationTargetException){
            modelAndView.addObject("head",new HEAD(RETURN.INVOCATION_TARGET_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof NoSuchMethodException){
            modelAndView.addObject("head",new HEAD(RETURN.NO_SUCH_METHOD_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof SecurityException){
            modelAndView.addObject("head",new HEAD(RETURN.SECURITY_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof ClassCastException){
            modelAndView.addObject("head",new HEAD(RETURN.CLASS_CAST_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof BeansException){
            modelAndView.addObject("head",new HEAD(RETURN.BEAN_EXPRESSION,request));
            return modelAndView;
        }else {
            modelAndView.addObject("head",new HEAD(RETURN.EXPRESSION,request));
            return modelAndView;
        }
    }
}
