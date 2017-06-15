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
        this.setUrl("/agile/DictionaryDataService/update");
        this.setMethod("post");

        this.setParameter("setName", "");
        this.setParameter("setValue", "");
        this.setParameter("setDicCode", "");
        this.setParameter("setIsFixed", "");
        this.setParameter("setCode", "");

        this.processor();
    }
    @Test
    public void delete() throws Exception {
        this.setUrl("/agile/DictionaryDataService/delete");
        this.setMethod("post");

        this.setParameter("setName", "");
        this.setParameter("setValue", "");
        this.setParameter("setDicCode", "");
        this.setParameter("setIsFixed", "");
        this.setParameter("setCode", "");

        this.processor();
    }
    @Test
    public void save() throws Exception {
        this.setUrl("/agile/DictionaryDataService/save");
        this.setMethod("post");

        this.setParameter("setName", "");
        this.setParameter("setValue", "");
        this.setParameter("setDicCode", "");
        this.setParameter("setIsFixed", "");
        this.setParameter("setCode", "");

        this.processor();
    }
    @Test
    public void query() throws Exception {
        this.setUrl("/agile/DictionaryDataService/query");
        this.setMethod("post");

        this.setParameter("setName", "");
        this.setParameter("setValue", "");
        this.setParameter("setDicCode", "");
        this.setParameter("setIsFixed", "");
        this.setParameter("setCode", "");

        this.processor();
    }

}