package com.agile.mvc.controller;


import com.agile.constant.RETURN;
import com.agile.format.HEAD;
import com.agile.mvc.service.InterfaceBusiness;
import com.agile.util.ObjectUtil;
import com.agile.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by tongmeng on 2017/1/6.
 */
@Controller
@Scope("prototype")
public class MainController {
    //spring上下文
    @Autowired
    ApplicationContext applicationContext;
    //日志工具
    protected Logger logger = LogManager.getLogger(this.getClass());
    //认证令牌
    private String authToken;
    //服务对象
    private InterfaceBusiness service;



    /**
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param service 服务名
     * @param method 方法名
     * @param forward 转发信息
     * @param authToken 认证令牌
     * @param cacheToken 缓存令牌
     * @return
     */
    @RequestMapping(value = "/{service}/{method}",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView processor(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable String service,
            @PathVariable String method,
            @RequestParam(value = "forward", required = false) String forward,
            @RequestParam(value = "auth-token", required = false) String authToken,
            @RequestParam(value = "cache-token", required = false) String cacheToken
    ) throws Exception {

        ModelAndView modelAndView = new ModelAndView();//响应视图对象
        service =  StringUtil.toServerName(service);//设置服务名
        method =  StringUtil.toMethodName(method);//设置服务名

        //-------------------------------参数校验-----------------------------------
        if (!StringUtil.isEmpty(forward)){//如果存在转发请求则将其放入返回结果信息当中
            modelAndView.setViewName(URLEncoder.encode(forward, "UTF-8"));
        }
        if (StringUtil.isEmpty(service) || ObjectUtil.isEmpty(this.getService(service))){//判断服务存在
            modelAndView.addObject("head",new HEAD(RETURN.NO_SERVICE,request));
            return modelAndView;
        }
        if (StringUtil.isEmpty(method)){//判断方法存在
            modelAndView.addObject("head",new HEAD(RETURN.NO_METHOD,request));
            return modelAndView;
        }else {

            //调用目标方法前处理入参
            handleRequestUrl(request,authToken,service,method);

            //调用目标方法
            RETURN returnState = this.getService().excuteMethod(method);

            //调用目标方法后处理视图
            modelAndView.addObject("head",new HEAD(returnState,request));

            //响应数据装填
            modelAndView.addObject("result",this.getService().getOutParam());
        }
        return modelAndView;
    }

    /**
     * 根据服务名在Spring上下文中获取服务bean
     * @param serviceName   服务名
     * @return  服务bean
     */
    private InterfaceBusiness getService(String serviceName){
        try {
            Object serviceTry = this.getApplicationContext().getBean(serviceName);
            service = (InterfaceBusiness) serviceTry;
            this.setService(service);
            return service;
        }catch (Exception e){
            return null;
        }
    }
    //处理入参
    private void handleRequestUrl(HttpServletRequest request, String authToken,String service,String method) throws UnsupportedEncodingException {
        HashMap<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("authoken",authToken);
        inParam.put("service",service);
        inParam.put("method",method);
        inParam.put("ip", request.getRemoteAddr());
        inParam.put("url", request.getRequestURL());

        String queryString = request.getQueryString();
        if (!StringUtil.isEmpty(queryString)){
            String[] params = queryString.split("&"),paramContainer=null;
            for (String param:params) {
                paramContainer = param.split("=");
                if (paramContainer.length == 2){
                    inParam.put(paramContainer[0],paramContainer[1]);
                }
            }
        }
        //将处理过的所有请求参数传入调用服务对象
        this.getService().setInParam(inParam);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public InterfaceBusiness getService() {
        return service;
    }

    public void setService(InterfaceBusiness service) {
        this.service = service;
    }

}
