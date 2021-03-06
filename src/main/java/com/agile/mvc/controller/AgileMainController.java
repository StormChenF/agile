package com.agile.mvc.controller;


import com.agile.common.base.RETURN;
import com.agile.common.base.AgileHead;
import com.agile.common.server.AgileServiceInterface;
import com.agile.common.util.ObjectUtil;
import com.agile.common.util.ServletUtil;
import com.agile.common.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by 佟盟 on 2017/1/6
 */
@Controller
@Scope("prototype")
public class AgileMainController {
    //日志工具
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(this.getClass());
    //上下文
    private final ApplicationContext applicationContext;
    //服务对象
    private AgileServiceInterface service;
    //工程名
    @Value("${agile.project.name}")
    private String moduleName;
    //免认证模块
    @Value("${agile.project.non_authentication_module}")
    private String freeAuthenticationModule;
    //免认证服务
    @Value("${agile.project.non_authentication_service}")
    private String freeAuthenticationService;

    @Autowired
    public AgileMainController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 非法请求处理器
     * @param request 请求对象
     * @return 响应视图
     */
    @RequestMapping(value = {"/","/*","/*/*","/*/*/*/*/**"})
    public ModelAndView processor(HttpServletRequest request){
        //初始化参数
        ModelAndView modelAndView = new ModelAndView();//响应视图对象

        //判断模块存在
        modelAndView.addObject("head",new AgileHead(RETURN.NO_COMPLETE,request));
        return modelAndView;
    }

    /**
     * agile框架处理器
     * @param request 请求对象
     * @param response 响应对象
     * @param service 服务名
     * @param method 方法名
     * @param forward 转发信息
     * @param authToken 认证令牌
     * @return 响应试图数据
     * @throws IOException 流异常
     * @throws IllegalAccessException 非法访问异常
     * @throws IllegalArgumentException 非法参数异常
     * @throws InvocationTargetException 调用目标异常
     * @throws NoSuchMethodException 没有这样的方法异常
     * @throws SecurityException 安全异常
     */
    @RequestMapping(value = "/{module}/{service}/{method}")
    public ModelAndView processor(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable String module,
            @PathVariable String service,
            @PathVariable String method,
            @RequestParam(value = "forward", required = false) String forward,
            @RequestParam(value = "auth-token", required = false) String authToken,
            @RequestParam(value = "file-path", required = false) String filePath,
            @RequestParam(value = "file-name", required = false) String fileName
    ) throws IOException,IllegalAccessException, IllegalArgumentException, InvocationTargetException,NoSuchMethodException, SecurityException{

        //判断文件下载
        if(!StringUtil.isEmpty(filePath) && !StringUtil.isEmpty(fileName)){
            this.downLoadFile(response,filePath,fileName);
            return null;
        }

        //初始化参数
        ModelAndView modelAndView = new ModelAndView();//响应视图对象
        service =  StringUtil.toLowerName(service);//设置服务名
        method =  StringUtil.toLowerName(method);//设置方法名

        //判断模块存在
        if (StringUtil.isEmpty(module) || !module.equals(moduleName)){
            modelAndView.addObject("head",new AgileHead(RETURN.NO_MODULE,request));
            return modelAndView;
        }

        //判断服务存在
        if (StringUtil.isEmpty(service) || ObjectUtil.isEmpty(this.getService(service))){
            modelAndView.addObject("head",new AgileHead(RETURN.NO_SERVICE,request));
            return modelAndView;
        }

        //判断方法存在
        if (StringUtil.isEmpty(method)){
            modelAndView.addObject("head",new AgileHead(RETURN.NO_METHOD,request));
            return modelAndView;
        }

        //调用目标方法前处理入参
        handleRequestUrl(request,authToken,service,method);

        //调用目标方法
        RETURN returnState = this.getService().executeMethod(method);

        //判断是否转发
        if(!StringUtil.isEmpty(forward) && RETURN.SUCCESS.equals(returnState)){

            //过滤转发并获取请求参数，避免重复转发
            String afterParam = request.getQueryString().replaceFirst("forward[-_*%#$@+=()^!~`|.,/a-zA-Z0-9]+[&]?","");

            //服务间参数传递
            String beforeParam = StringUtil.fromMapToUrl(this.getService().getOutParam());

            //转发
            modelAndView.setView(new RedirectView(forward+(StringUtil.isEmpty(afterParam+beforeParam)?"":"?"+afterParam+beforeParam)));

            return modelAndView;
        }

        //调用目标方法后处理视图
        modelAndView.addObject("head",new AgileHead(returnState,request));

        //响应数据装填
        modelAndView.addObject("result",this.getService().getOutParam());

        return modelAndView;
    }

    /**
     * 根据服务名在Spring上下文中获取服务bean
     * @param serviceName   服务名
     * @return  服务bean
     */
    private AgileServiceInterface getService(String serviceName) throws BeansException,NullPointerException,ClassCastException {
        try {
            Object serviceTry = this.applicationContext.getBean(serviceName);
            service = (AgileServiceInterface) serviceTry;
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
     * @throws IOException 流异常
     */
    private void handleRequestUrl(HttpServletRequest request, String authToken,String service,String method) throws IOException {
        HashMap<String, Object> inParam = new HashMap<>();
        inParam.put("authToken",authToken);
        inParam.put("app",moduleName);
        inParam.put("service",service);
        inParam.put("method",method);
        inParam.put("ip", ServletUtil.getCustomerIPAddr(request));
        inParam.put("url", request.getRequestURL());

        //---------------------------------请求参数解析------------------------------------
        String queryString = request.getQueryString();
        if (!StringUtil.isEmpty(queryString)){
            String[] params = queryString.split("&"),paramContainer;
            for (int i = 0 ; i < params.length ; i++) {
                paramContainer = params[i].split("=");
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
     * @throws IOException 流异常
     */
    private void downLoadFile(HttpServletResponse response,String path,String name) throws IOException {
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

    private AgileServiceInterface getService() {
        return service;
    }

    private void setService(AgileServiceInterface service) {
        this.service = service;
    }

}
