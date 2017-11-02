package com.agile.common.servlet;

import com.agile.common.config.LoggerFactoryConfig;
import com.agile.common.filter.CORSFilter;
import com.agile.common.filter.SecurityCsrfHeaderFilter;
import com.agile.common.listener.CacheListener;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;
import org.springframework.web.util.WebAppRootListener;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Created by 佟盟 on 2017/9/27
 */
public class DispatcherServletInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(@NotNull ServletContext servletContext) {
        servletContext.setInitParameter("webAppRootKey","agile.root");
        servletContext.setSessionTimeout(30);
        servletContext.setRequestCharacterEncoding("UTF-8");
        servletContext.setResponseCharacterEncoding("UTF-8");

        /*
          优先启动log4j2配置
         */
        ConfigurationFactory.setConfigurationFactory(LoggerFactoryConfig.getInstance());

        /*
          编码过滤器
         */
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("EncodingFilter", CharacterEncodingFilter.class);
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.setAsyncSupported(true);
        encodingFilter.addMappingForUrlPatterns(null, false, "/*");

        /*
          Druid监控过滤器
         */
        FilterRegistration.Dynamic druidWebStatFilter = servletContext.addFilter("DruidWebStatFilter", WebStatFilter.class);
        druidWebStatFilter.setInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        druidWebStatFilter.setInitParameter("sessionStatMaxCount","100000");
        druidWebStatFilter.addMappingForUrlPatterns(null, false, "/*");

        /*
          Security过滤器
         */
        FilterRegistration.Dynamic securityFilter = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
        securityFilter.addMappingForUrlPatterns(null, false, "/*");

        /*
          CSRF过滤器
         */
        FilterRegistration.Dynamic agileCsrfHeaderFilter = servletContext.addFilter("agileCsrfHeaderFilter", SecurityCsrfHeaderFilter.class);
        agileCsrfHeaderFilter.setInitParameter("tokenName","X-CSRF-TOKEN");
        agileCsrfHeaderFilter.addMappingForUrlPatterns(null, false, "/*");

        /*
          CORS过滤器
         */
        FilterRegistration.Dynamic corsFilter = servletContext.addFilter("CORSFilter", CORSFilter.class);
        corsFilter.setInitParameter("allowOrigin","*");
        corsFilter.setInitParameter("allowMethods","GET,POST,PUT,DELETE,OPTIONS");
        corsFilter.setInitParameter("allowCredentials","true");
        corsFilter.setInitParameter("allowHeaders","Content-Type,X-CSRF-TOKEN,JSESSIONID");
        corsFilter.addMappingForUrlPatterns(null, false, "/*");

        /*
          验证码Servlet
         */
        ServletRegistration.Dynamic kaptchaServlet = servletContext.addServlet("VerificationCodeServlet", KaptchaServlet.class);
        kaptchaServlet.addMapping("/verification");

        /*
          DruidServlet
         */
        ServletRegistration.Dynamic druidStatViewServlet = servletContext.addServlet("DruidStatViewServlet", StatViewServlet.class);
        druidStatViewServlet.setInitParameter("resetEnable","true");
        druidStatViewServlet.setInitParameter("loginUsername","admin");
        druidStatViewServlet.setInitParameter("loginPassword","admin");
        druidStatViewServlet.addMapping("/druid/*");

        /*
          SpringDispatcherServlet
         */
        ServletRegistration.Dynamic springDispatcherServlet = servletContext.addServlet("SpringDispatcherServlet", DispatcherServlet.class);
        springDispatcherServlet.setInitParameter("contextClass","org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
        springDispatcherServlet.setInitParameter("contextConfigLocation","com.agile.common.config.SpringConfig");
        springDispatcherServlet.setInitParameter("dispatchOptionsRequest","true");
        springDispatcherServlet.setLoadOnStartup(1);
        springDispatcherServlet.addMapping("/*");

//        servletContext.addListener(Log4jListener.class);

        /*
          初始化缓存监听
         */
        servletContext.addListener(CacheListener.class);

        /*
          内存溢出监听
         */
        servletContext.addListener(IntrospectorCleanupListener.class);

        /*
          session监听
         */
        servletContext.addListener(HttpSessionEventPublisher.class);

        /*
          项目根目录监听
         */
        servletContext.addListener(WebAppRootListener.class);


    }
}
