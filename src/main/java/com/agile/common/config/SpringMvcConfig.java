package com.agile.common.config;

import com.agile.common.viewResolver.JsonViewResolver;
import com.agile.common.viewResolver.XmlViewResolver;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 佟盟 on 2017/8/22
 */
@Configuration
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer , EnvironmentAware {
    private Environment env;

    private final JsonViewResolver jsonViewResolver;

    private final XmlViewResolver xmlViewResolver;

    @Autowired
    public SpringMvcConfig(JsonViewResolver jsonViewResolver, XmlViewResolver xmlViewResolver) {
        this.jsonViewResolver = jsonViewResolver;
        this.xmlViewResolver = xmlViewResolver;
    }

    /**
     * 视图解析器
     * 配置视图解析器视图列表
     * @param manager 略
     * @return 视图解析器
     */
    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager){
        List<ViewResolver> list = new ArrayList<>();
        list.add(jsonViewResolver);
        list.add(xmlViewResolver);

        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
        resolver.setOrder(1);
        resolver.setViewResolvers(list);
        return resolver;
    }

    /**
     * 文件上传配置
     * @return 文件上传下载解析器
     */
    @Bean
    public CommonsMultipartResolver contentCommonsMultipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(env.getProperty("agile.upload.max_upload_size",long.class));
        resolver.setDefaultEncoding(env.getProperty("agile.upload.default_encoding"));
        return resolver;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        Map<String, MediaType> map = new HashMap<>();
        map.put("atom",MediaType.APPLICATION_ATOM_XML);
        map.put("form",MediaType.APPLICATION_FORM_URLENCODED);
        map.put("json",MediaType.APPLICATION_JSON);
        map.put("octet-stream",MediaType.APPLICATION_OCTET_STREAM);
        map.put("event-stream",MediaType.TEXT_EVENT_STREAM);
        map.put("pdf",MediaType.APPLICATION_PDF);
        map.put("xhtml",MediaType.APPLICATION_XHTML_XML);
        map.put("gif",MediaType.IMAGE_GIF);
        map.put("jpeg",MediaType.IMAGE_JPEG);
        map.put("png",MediaType.IMAGE_PNG);
        map.put("multipart",MediaType.MULTIPART_FORM_DATA);
        map.put("html",MediaType.TEXT_HTML);
        map.put("plain",MediaType.TEXT_PLAIN);
        map.put("markdown",MediaType.TEXT_MARKDOWN);

        configurer.ignoreAcceptHeader(true)
                .favorPathExtension(true)
                .favorParameter(false)
                .defaultContentType(MediaType.APPLICATION_JSON_UTF8)
                .mediaTypes(map);
    }

    @Override
    public void setEnvironment(@NotNull Environment environment) {
        env = environment;
    }
}
