package com.agile.mvc.controller;

import com.agile.common.base.ResponseHead;
import com.agile.common.base.Constant;
import com.agile.common.base.RETURN;
import com.agile.common.exception.NoSuchRequestServiceException;
import com.agile.common.service.ServiceInterface;
import com.agile.common.util.*;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 主控制层
 * Created by 佟盟 on 2017/8/22
 */
@Controller
public class MainController {

    private static ThreadLocal<ServiceInterface> service = new ThreadLocal<>();
    private static ThreadLocal<HttpServletRequest> request = new ThreadLocal<>();
//    /**
//     * 非法请求处理器
//     */
//    @RequestMapping(value = {"/","/*","/*/*/*/**"})
//    @Order(2)
//    public void processor() throws UnlawfulRequestException {
//        throw new UnlawfulRequestException();
//    }

    /**
     * agile框架处理器
     * @param service 服务名
     * @param method 方法名
     * @return 响应试图数据
     */
    @RequestMapping(value = "/{service}/{method}")
    public Object processor(
            HttpServletRequest currentRequest,
            @PathVariable String service,
            @PathVariable String method
    ) throws Throwable {
        //清理缓存
        clear();

        //初始化参数
        ModelAndView modelAndView = new ModelAndView();//响应视图对象
        service =  StringUtil.toLowerName(service);//设置服务名
        method = StringUtil.toLowerName(method);//设置方法名
        initService(service);
        request.set(currentRequest);

        //调用目标方法前处理入参
        handleRequestUrl();

        //调用目标方法
        RETURN returnState = getService().executeMethod(method,getService());

        //判断是否跳转
        Map<String, Object> outParam = getService().getOutParam();
        Object forward = outParam.get(Constant.JumpMethod.forward.getCode());
        if(!ObjectUtil.isEmpty(forward) && !StringUtil.isEmpty(forward.toString()) && RETURN.SUCCESS.equals(returnState)){
            return jump(forward.toString(),Constant.JumpMethod.forward);
        }
        Object redirect = outParam.get(Constant.JumpMethod.redirect.getCode());
        if(!ObjectUtil.isEmpty(redirect) && !StringUtil.isEmpty(redirect.toString()) && RETURN.SUCCESS.equals(returnState)){
            return jump(redirect.toString(),Constant.JumpMethod.redirect);
        }

        //调用目标方法后处理视图
        modelAndView.addObject(Constant.ResponseAbout.HEAD, new ResponseHead(returnState));

        //响应数据装填
        modelAndView.addObject(Constant.ResponseAbout.RESULT, getService().getOutParam());

        //清理缓存
        clear();

        return modelAndView;
    }

    /**
     * 由于线程池的使用与threadLocal冲突,前后需要清理缓存
     */
    private void clear(){
        service.remove();
        request.remove();
    }

    /**
     * 转发
     * @param resourceUrl 转发路径
     * @param jumpMethod 跳转方式
     */
    private ModelAndView jump(String resourceUrl,Constant.JumpMethod jumpMethod){
        StringBuilder url = new StringBuilder(jumpMethod.getPre());
        if(!resourceUrl.startsWith("http") && !resourceUrl.startsWith("/")){
            url.append("/");
        }
        url.append(resourceUrl);
        if(!resourceUrl.contains(Constant.RegularAbout.QUESTION_MARK)){
            url.append(Constant.RegularAbout.QUESTION_MARK);
        }

        //服务间参数传递
        Map<String, Object> outParam = getService().getOutParam();
        outParam.remove(jumpMethod.getCode());

        Map<String, Object> inParam = getService().getInParam();
        ModelAndView model = new ModelAndView(url.toString());
        model.addAllObjects(outParam);
        model.addAllObjects(inParam);
        return model;
    }

    /**
     * 根据服务名在Spring上下文中获取服务bean
     * @param serviceName   服务名
     */
    private void initService(String serviceName)throws NoSuchRequestServiceException {
        try {
            Object service = FactoryUtil.getBean(serviceName);
            setService((ServiceInterface) service);
        }catch (Exception e){
            throw new NoSuchRequestServiceException();
        }
    }

    /**
     * 根据servlet请求、认证信息、目标服务名、目标方法名处理入参
     */
    private void handleRequestUrl() {
        getService().initInParam();
        HttpServletRequest currentRequest = request.get();
        Map<String,Object> inParam = new HashMap<>();
        if (currentRequest.getParameterMap().size()>0){
            for (Map.Entry<String,String[]> map:currentRequest.getParameterMap().entrySet() ) {
                inParam.put(map.getKey(),map.getValue());
            }
        }

        //判断是否存在文件上传
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(currentRequest.getSession().getServletContext());
        if (multipartResolver.isMultipart(currentRequest)){
            inParam.putAll(FileUtil.getFileFormRequest(currentRequest));
        }

        //将处理过的所有请求参数传入调用服务对象
        getService().setInParam(inParam);
    }

    /**
     * 文件下载
     * @param path 文件路径
     * @param fileName 文件名
     * @return 文件流
     * @throws FileNotFoundException 流异常
     */
    @RequestMapping("/download")
    private ResponseEntity<byte[]> downloadFile(@RequestParam(value = "path") String path ,@RequestParam(value = "fileName") String fileName) throws FileNotFoundException{
        File file = new File(path,fileName);
        byte[] byteFile;
        try {
            byteFile = FileUtils.readFileToByteArray(file);
        }catch (IOException e){
            throw new FileNotFoundException();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(file.length());
        headers.setContentDispositionFormData(Constant.HeaderAbout.ATTACHMENT,new String(fileName.getBytes(Charset.forName("UTF-8")),Charset.forName("ISO-8859-1")));
        return new ResponseEntity<>(byteFile, headers, HttpStatus.CREATED);
    }

    private ServiceInterface getService() {
        return service.get();
    }

    private void setService(ServiceInterface service) {
        MainController.service.set(service);
    }

}
