package com.agile.mvc.service;

import com.agile.mvc.controller.AgileMainControllerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
* Created by 佟盟
*/
@RunWith(SpringJUnit4ClassRunner.class)
public class ${className}Test extends AgileMainControllerTest {
<#list methodList as method>
    @Test
    public void ${method.methodName}() throws Exception {
        this.setUrl("${method.url}");
        this.setMethod("post");

        this.setParameter("name", "张三");
        this.setParameter("password", "123456");

        this.processor();
    }
</#list>

}