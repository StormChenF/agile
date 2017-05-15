package com.agile.mvc.controller;

import com.agile.common.util.JSONUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;


/**
 * Created by mydeathtrial on 2017/5/5
 */
@ContextConfiguration(locations = "classpath:com/agile/configure/spring-container.xml")
@Transactional(transactionManager = "transactionManager",isolation = Isolation.READ_COMMITTED)
public class AgileMainControllerTest {
    //日志工具
    private Logger logger = LogManager.getLogger(this.getClass());
    private static RequestMappingHandlerMapping handlerMapping;
    private static RequestMappingHandlerAdapter handlerAdapter;
    private static MockHttpServletRequest request;

    /**
     * 注入spring上下文
     */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 启动测试之前，需要获取springMVC的请求映射与请求适配器，以便模拟服务器请求
     */
    @Before
    public void setUp() {
        handlerMapping = (RequestMappingHandlerMapping) this.applicationContext.getBean("requestMappingHandlerMapping");
        handlerAdapter = (RequestMappingHandlerAdapter) this.applicationContext.getBean("requestMappingHandlerAdapter");
        request = new MockHttpServletRequest();
    }

    /**
     * 提供给每个测试用例，用于设置请求服务的地址路径
     * @param url 地址
     */
    protected void setUrl(String url){
        request.setServletPath(url);
    }

    /**
     * 提供给每个测试用例，用于设置请求参数
     * @param key 索引字符串
     * @param value 值
     */
    protected void setParameter(String key,String value){
        request.setParameter(key,value);
    }

    /**
     * 提供给每个测试用力，用于设置请求POST/GET方法
     * @param method emn：post,get
     */
    protected void setMethod(String method){
        request.setMethod(method);
    }

    /**
     * 测试用例核心处理器
     * 每个测试用例将继承于主测试控制器，并最终通过该核心处理器将测试请求发送至指定服务，并打印服务响应信息
     * @return 视图，每个测试用例可通过该试图获取被测试服务响应信息
     * @throws Exception 异常
     */
    protected ModelAndView processor() throws Exception {
        request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
        HandlerExecutionChain chain = handlerMapping.getHandler(request);
        ModelAndView model = null;
        try {
            model = handlerAdapter.handle(request, new MockHttpServletResponse(), chain.getHandler());
            logger.info(JSONUtil.toJSONString(model.getModel().get("head")));
            logger.trace(JSONUtil.toJSONString(model.getModel().get("result")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

}