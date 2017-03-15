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
 * Created by 佟盟 on 2017/2/23
 */
public class AgileExceptionHandler implements HandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();//响应视图对象
        if(e instanceof ParameterExpression){
            modelAndView.addObject("head",new AgileHead(RETURN.PARAMETER_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof SQLException){
            modelAndView.addObject("head",new AgileHead(RETURN.SQL_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof HibernateException){
            modelAndView.addObject("head",new AgileHead(RETURN.HIBERNATE_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof DateTimeException){
            modelAndView.addObject("head",new AgileHead(RETURN.DATETIME_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof NullPointerException){
            modelAndView.addObject("head",new AgileHead(RETURN.NULL_POINTER_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof ParseException){
            modelAndView.addObject("head",new AgileHead(RETURN.PARSE_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof IOException){
            modelAndView.addObject("head",new AgileHead(RETURN.IO_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof TimeoutException){
            modelAndView.addObject("head",new AgileHead(RETURN.TIMEOUT_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof IllegalAccessException){
            modelAndView.addObject("head",new AgileHead(RETURN.IIIEGAL_ACCESS_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof IllegalArgumentException){
            modelAndView.addObject("head",new AgileHead(RETURN.IIIEGAL_ARGUMENT_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof InvocationTargetException){
            modelAndView.addObject("head",new AgileHead(RETURN.INVOCATION_TARGET_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof NoSuchMethodException){
            modelAndView.addObject("head",new AgileHead(RETURN.NO_METHOD,request));
            return modelAndView;
        }else if(e instanceof SecurityException){
            modelAndView.addObject("head",new AgileHead(RETURN.SECURITY_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof ClassCastException){
            modelAndView.addObject("head",new AgileHead(RETURN.CLASS_CAST_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof BeansException){
            modelAndView.addObject("head",new AgileHead(RETURN.BEAN_EXPRESSION,request));
            return modelAndView;
        }else {
            modelAndView.addObject("head",new AgileHead(RETURN.EXPRESSION,request));
            return modelAndView;
        }
    }
}
