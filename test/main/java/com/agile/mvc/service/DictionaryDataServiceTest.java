package com.agile.mvc.service;

import com.agile.mvc.controller.AgileMainControllerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
* Created by 佟盟
*/
@RunWith(SpringJUnit4ClassRunner.class)
public class DictionaryDataServiceTest extends AgileMainControllerTest {
    @Test
    public void update() throws Exception {
        this.setUrl("/DictionaryDataService/update");
        this.setMethod("post");

        this.setParameter("setName", "");
        this.setParameter("setValue", "");
        this.setParameter("setCode", "");
        this.setParameter("setIsFixed", "");
        this.setParameter("setDicCode", "");

        this.processor();
    }

    @Test
    public void delete() throws Exception {
        this.setUrl("/DictionaryDataService/delete");
        this.setMethod("post");

        this.setParameter("setName", "");
        this.setParameter("setValue", "");
        this.setParameter("setCode", "");
        this.setParameter("setIsFixed", "");
        this.setParameter("setDicCode", "");

        this.processor();
    }

    @Test
    public void save() throws Exception {
        this.setUrl("/DictionaryDataService/save");
        this.setMethod("post");

        this.setParameter("setName", "");
        this.setParameter("setValue", "");
        this.setParameter("setCode", "");
        this.setParameter("setIsFixed", "");
        this.setParameter("setDicCode", "");

        this.processor();
    }

    @Test
    public void query() throws Exception {
        this.setUrl("/DictionaryDataService/query");
        this.setMethod("post");

        this.setParameter("setName", "");
        this.setParameter("setValue", "");
        this.setParameter("setCode", "");
        this.setParameter("setIsFixed", "");
        this.setParameter("setDicCode", "");

        this.processor();
    }


}