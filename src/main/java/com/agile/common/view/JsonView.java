package com.agile.common.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Created by 佟盟 on 2017/8/1
 */
@Component
public class JsonView extends MappingJackson2JsonView {
    public JsonView() {
        Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean = new Jackson2ObjectMapperFactoryBean();
        jackson2ObjectMapperFactoryBean.setIndentOutput(true);
        jackson2ObjectMapperFactoryBean.setSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setPrettyPrint(true);
    }
}
