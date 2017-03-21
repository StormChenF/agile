package com.agile.common.base;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    /**
     * 日志工具
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();//响应视图对象
        if(e instanceof ParameterExpression){
            logger.error(RETURN.PARAMETER_EXPRESSION.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.PARAMETER_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof SQLException){
            logger.error(RETURN.SQL_EXPRESSION.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.SQL_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof HibernateException){
            logger.error(RETURN.HIBERNATE_EXPRESSION.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.HIBERNATE_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof DateTimeException){
            logger.error(RETURN.DATETIME_EXPRESSION.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.DATETIME_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof NullPointerException){
            logger.error(RETURN.NULL_POINTER_EXPRESSION.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.NULL_POINTER_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof ParseException){
            logger.error(RETURN.PARSE_EXPRESSION.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.PARSE_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof IOException){
            logger.error(RETURN.IO_EXPRESSION.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.IO_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof TimeoutException){
            logger.error(RETURN.TIMEOUT_EXPRESSION.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.TIMEOUT_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof IllegalAccessException){
            logger.error(RETURN.IIIEGAL_ACCESS_EXPRESSION.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.IIIEGAL_ACCESS_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof IllegalArgumentException){
            logger.error(RETURN.IIIEGAL_ARGUMENT_EXPRESSION.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.IIIEGAL_ARGUMENT_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof InvocationTargetException){
            logger.error(RETURN.INVOCATION_TARGET_EXPRESSION.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.INVOCATION_TARGET_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof NoSuchMethodException){
            logger.error(RETURN.NO_METHOD.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.NO_METHOD,request));
            return modelAndView;
        }else if(e instanceof SecurityException){
            logger.error(RETURN.SECURITY_EXPRESSION.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.SECURITY_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof ClassCastException){
            logger.error(RETURN.CLASS_CAST_EXPRESSION.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.CLASS_CAST_EXPRESSION,request));
            return modelAndView;
        }else if(e instanceof BeansException){
            logger.error(RETURN.BEAN_EXPRESSION.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.BEAN_EXPRESSION,request));
            return modelAndView;
        }else {
            logger.error(RETURN.EXPRESSION.getMsg());
            modelAndView.addObject("head",new AgileHead(RETURN.EXPRESSION,request));
            return modelAndView;
        }
    }
}
