package com.agile.mvc.controller;


import com.agile.common.base.RETURN;
import com.agile.common.base.HEAD;
import com.agile.common.base.InterfaceBusiness;
import com.agile.common.util.ObjectUtil;
import com.agile.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

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
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    //认证令牌
    private String authToken;
    //服务对象
    private InterfaceBusiness service;
    //工程名
    @Value("${agile.project.name}")
    private String moduleName;
    //免认证模块
    @Value("${agile.project.non_authrntication_moudule}")
    private String nonAuthrnticationMoudule;
    //免认证服务
    @Value("${agile.project.non_authrntication_service}")
    private String nonAuthrnticationService;

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
    @RequestMapping(value = "/{module}/{service}/{method}",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView processor(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable String module,
            @PathVariable String service,
            @PathVariable String method,
            @RequestParam(value = "forward", required = false) String forward,
            @RequestParam(value = "auth-token", required = false) String authToken,
            @RequestParam(value = "cache-token", required = false) String cacheToken,
            @RequestParam(value = "file-path", required = false) String filePath,
            @RequestParam(value = "file-name", required = false) String fileName
    ) throws Exception {
        //判断文件下载
        if(!StringUtil.isEmpty(filePath) && !StringUtil.isEmpty(fileName)){
            this.downLoadFile(response,filePath,fileName);
            return null;
        }

        //初始化参数
        ModelAndView modelAndView = new ModelAndView();//响应视图对象
        service =  StringUtil.toServerName(service);//设置服务名
        method =  StringUtil.toMethodName(method);//设置服务名

        //判断模块存在
        if (StringUtil.isEmpty(module) || !module.equals(moduleName)){
            modelAndView.addObject("head",new HEAD(RETURN.NO_MODULE,request));
            return modelAndView;
        }

        //判断服务存在
        if (StringUtil.isEmpty(service) || ObjectUtil.isEmpty(this.getService(service))){
            modelAndView.addObject("head",new HEAD(RETURN.NO_SERVICE,request));
            return modelAndView;
        }

        //判断方法存在
        if (StringUtil.isEmpty(method)){
            modelAndView.addObject("head",new HEAD(RETURN.NO_METHOD,request));
            return modelAndView;
        }else {
            //调用目标方法前处理入参
            handleRequestUrl(request,response,authToken,service,method);

            //调用目标方法
            RETURN returnState = this.getService().excuteMethod(method);

            //调用目标方法后处理视图
            modelAndView.addObject("head",new HEAD(returnState,request));

            //响应数据装填
            modelAndView.addObject("result",this.getService().getOutParam());
        }

        //判断转发存在
        if (!StringUtil.isEmpty(forward)){//如果存在转发请求则将其放入返回结果信息当中
            modelAndView.setViewName(URLEncoder.encode(forward, "UTF-8"));
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
            this.getApplicationContext().getBeanDefinitionNames();
            Object serviceTry = this.getApplicationContext().getBean(serviceName);
            service = (InterfaceBusiness) serviceTry;
            this.setService(service);
            return service;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 根据servlet请求、认证信息、目标服务名、目标方法名处理入参
     * @param request   servlet请求
     * @param authToken 认证信息
     * @param service   目标服务名
     * @param method    目标方法名
     * @throws UnsupportedEncodingException
     */
    private void handleRequestUrl(HttpServletRequest request,HttpServletResponse response, String authToken,String service,String method) throws IOException {
        HashMap<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("authoken",authToken);
        inParam.put("app",moduleName);
        inParam.put("service",service);
        inParam.put("method",method);
        inParam.put("ip", request.getRemoteAddr());
        inParam.put("url", request.getRequestURL());

        //---------------------------------请求参数解析------------------------------------
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

        //---------------------------------文件上传解析------------------------------------
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //获取所有文件提交的input名
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            while (iterator.hasNext()){
                MultipartFile file = multipartHttpServletRequest.getFile(iterator.next());
                if (ObjectUtil.isEmpty(file)){
                    //取得当前文件名
                    String fileName = file.getOriginalFilename();
                    //判断文件是否存在
                    if (StringUtil.isEmpty(fileName)){
                        inParam.put(fileName,file);
                    }
                }
            }
        }

        //将处理过的所有请求参数传入调用服务对象
        this.getService().setInParam(inParam);
    }

    /**
     * 文件下载
     * @param response  响应对象
     * @param path  文件存储路径
     * @param name  文件名
     * @throws IOException
     */
    public void downLoadFile(HttpServletResponse response,String path,String name) throws IOException {
        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;
        try {
            long fileLength = new File(path).length();
            response.setContentType("application/force-download");
            response.setHeader("Content-Length", String.valueOf(fileLength));
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(name.getBytes("utf-8"), "utf-8"));
            bis = new BufferedInputStream(new FileInputStream(path));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
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
