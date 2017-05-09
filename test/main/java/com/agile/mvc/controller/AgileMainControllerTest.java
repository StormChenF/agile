package com.agile.mvc.controller;

import com.agile.common.util.JSONUtil;
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


/**
 * Created by mydeathtrial on 2017/5/5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(name = "context",locations = "classpath:com/agile/configure/spring-container.xml")
@Transactional(transactionManager = "transactionManager",isolation = Isolation.READ_COMMITTED)
public class AgileMainControllerTest {
    private static AgileMainControllerTest agileMainControllerTest;
    private static RequestMappingHandlerMapping handlerMapping;
    private static RequestMappingHandlerAdapter handlerAdapter;
    private static MockHttpServletRequest request;

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        handlerMapping = (RequestMappingHandlerMapping) this.applicationContext.getBean("requestMappingHandlerMapping");
        handlerAdapter = (RequestMappingHandlerAdapter) this.applicationContext.getBean("requestMappingHandlerAdapter");
        request = new MockHttpServletRequest();
    }

    @After
    public void tearDown() throws Exception {
        request = null;
    }

    protected MockHttpServletRequest getRequest(){
        return this.request;
    }

    protected void setUrl(String url){
        this.request.setServletPath(url);
    }

    protected void setParameter(String key,String value){
        this.request.setParameter(key,value);
    }

    protected void setMethod(String method){
        this.request.setMethod(method);
    }

    protected ModelAndView processor(HttpServletRequest request) throws Exception {
        request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
        HandlerExecutionChain chain = handlerMapping.getHandler(request);
        ModelAndView model = null;
        try {
            model = handlerAdapter.handle(request, new MockHttpServletResponse(), chain.getHandler());
            System.out.println(JSONUtil.toJSONString(model.getModel().get("head")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

}