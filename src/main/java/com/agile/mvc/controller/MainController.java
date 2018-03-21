package com.agile.mvc.controller;

import com.agile.common.base.ResponseHead;
import com.agile.common.base.Constant;
import com.agile.common.base.RETURN;
import com.agile.common.exception.NoSuchRequestServiceException;
import com.agile.common.service.ServiceInterface;
import com.agile.common.util.*;
import org.apache.commons.io.FileUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
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

    private ThreadLocal<ServiceInterface> service = new ThreadLocal<>();
//    /**
//     * 非法请求处理器
//     */
//    @RequestMapping(value = {"/","/*","/*/*/*/**"})
//    public void processor() throws UnlawfulRequestException {
//        throw new UnlawfulRequestException();
//    }

    /**
     * agile框架处理器
     * @param request 请求对象
     * @param service 服务名
     * @param method 方法名
     * @param forward 转发信息
     * @return 响应试图数据
     */
    @RequestMapping(value = "/{service}/{method}")
    public ModelAndView processor(
            HttpServletRequest request,
            @PathVariable String service,
            @PathVariable String method,
            @RequestParam(value = "forward", required = false) String forward
    ) throws Throwable {
        //初始化参数
        ModelAndView modelAndView = new ModelAndView();//响应视图对象
        service =  StringUtil.toLowerName(service);//设置服务名
        method = StringUtil.toLowerName(method);//设置方法名
        initService(service);

        //调用目标方法前处理入参
        handleRequestUrl(request);

        //调用目标方法
        RETURN returnState = this.getService().executeMethod(method,this.getService());

        //判断是否转发
        if (!StringUtil.isEmpty(forward) && RETURN.SUCCESS.equals(returnState)) {
            StringBuilder url = new StringBuilder(forward);
            if(!forward.startsWith(Constant.RegularAbout.SLASH)){
                url.insert(0,Constant.RegularAbout.SLASH);
            }

            //过滤转发并获取请求参数，避免重复转发
            String beforeParam = request.getQueryString().replaceFirst(Constant.RegularAbout.AFTER_PARAM, Constant.RegularAbout.NULL);

            //服务间参数传递
            String afterParam = StringUtil.fromMapToUrl(this.getService().getOutParam());


            url = (StringUtil.isEmpty(beforeParam) && StringUtil.isEmpty(afterParam))?url
                    :StringUtil.compareTo(beforeParam,afterParam)?url.append(Constant.RegularAbout.QUESTION_MARK).append(beforeParam).append(Constant.RegularAbout.AND).append(afterParam)
                    :url.append(Constant.RegularAbout.QUESTION_MARK).append(afterParam).append(Constant.RegularAbout.AND).append(beforeParam);
            url = url.toString().endsWith(Constant.RegularAbout.AND)?url.deleteCharAt(url.lastIndexOf(Constant.RegularAbout.AND)):url;

            //转发
            modelAndView.setView(new RedirectView(url.toString()));

            return modelAndView;
        }

        //调用目标方法后处理视图
        modelAndView.addObject(Constant.ResponseAbout.HEAD, new ResponseHead(returnState));

        //响应数据装填
        modelAndView.addObject(Constant.ResponseAbout.RESULT, this.getService().getOutParam());

        this.getService().clear();
        this.service.remove();

        return modelAndView;
    }

    /**
     * 根据服务名在Spring上下文中获取服务bean
     * @param serviceName   服务名
     */
    private void initService(String serviceName)throws NoSuchRequestServiceException {
        try {
            Object service = FactoryUtil.getBean(serviceName);
            this.setService((ServiceInterface) service);
        }catch (Exception e){
            throw new NoSuchRequestServiceException();
        }
    }

    /**
     * 根据servlet请求、认证信息、目标服务名、目标方法名处理入参
     * @param request   servlet请求
     */
    private void handleRequestUrl(HttpServletRequest request) {
        Map<String,Object> inParam = new HashMap<>();
        if (request.getParameterMap().size()>0){
            for (Map.Entry<String,String[]> map:request.getParameterMap().entrySet() ) {
                inParam.put(map.getKey(),map.getValue());
            }
        }

        //判断是否存在文件上传
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)){
            inParam.putAll(FileUtil.getFileFormRequest(request));
        }

        //将处理过的所有请求参数传入调用服务对象
        this.getService().setInParam(inParam);
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
        this.service.set(service);
    }

}
