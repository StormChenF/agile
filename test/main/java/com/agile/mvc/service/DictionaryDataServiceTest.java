package com.agile.mvc.service;

import com.agile.mvc.controller.AgileMainControllerTest;
import org.junit.Test;


/**
 * Created by mydeathtrial on 2017/5/4
 */
public class DictionaryDataServiceTest extends AgileMainControllerTest {
    @Test
    public void save() throws Exception {
        this.setUrl("/agile/dictionaryDataService/query");
        this.setMethod("post");
        this.setParameter("name", "张三");
        this.setParameter("password", "123456");
        this.processor(this.getRequest());
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void query() throws Exception {
    }
}