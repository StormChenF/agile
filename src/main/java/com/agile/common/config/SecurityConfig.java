package com.agile.common.config;

import com.agile.common.security.SecurityAuthenticationFilter;
import com.agile.common.security.SecurityAuthenticationProvider;
import com.agile.common.security.SecurityKaptchaAuthenticationFilter;
import com.agile.common.security.SecurityUserDetailsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import java.util.Collections;

/**
 * Created by 佟盟 on 2017/9/26
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements EnvironmentAware {

    private Environment env;
    private final RequestMatcher securityCsrfRequestMatcher;
    private final AuthenticationProvider securityAuthenticationProvider;
    private final SecurityUserDetailsService securityUserDetailsService;

    @Autowired
    public SecurityConfig(RequestMatcher securityCsrfRequestMatcher, SecurityAuthenticationProvider securityAuthenticationProvider, SecurityUserDetailsService securityUserDetailsService) {
        this.securityCsrfRequestMatcher = securityCsrfRequestMatcher;
        this.securityAuthenticationProvider = securityAuthenticationProvider;
        this.securityUserDetailsService = securityUserDetailsService;
    }

    //http://localhost:8080/login 输入正确的用户名密码 并且选中remember-me 则登陆成功，转到 index页面
    //再次访问index页面无需登录直接访问
    //访问http://localhost:8080/home 不拦截，直接访问，
    //访问http://localhost:8080/hello 需要登录验证后，且具备 “ADMIN”权限hasAuthority("ADMIN")才可以访问
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().authenticationEntryPoint(loginUrlAuthenticationEntryPoint())
                .and()
                .authorizeRequests().antMatchers("/**").access("isAuthenticated()")
                .antMatchers(env.getProperty("agile.security.not_login_url"),env.getProperty("agile.security.invalid_session_url"),"/druid/**", "/swagger**","/verification").permitAll()//访问：无需登录认证权限
//                .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
//                .antMatchers("/druid/*").hasAuthority("ADMIN") //登陆后之后拥有“ADMIN”权限才可以访问/hello方法，否则系统会出现“403”权限不足的提示
                .and()
                    .exceptionHandling()
                .and()
                    .addFilterBefore(new SecurityKaptchaAuthenticationFilter(env.getProperty("agile.security.login_url"), env.getProperty("agile.security.failure_url"),env.getProperty("agile.security.verification_code")), UsernamePasswordAuthenticationFilter.class)//验证码
                    .csrf()
                    .requireCsrfProtectionMatcher(securityCsrfRequestMatcher)//CSRF
                .and()
                    .addFilterAt(securityAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                    .rememberMe().key("e37f4b31-0c45-11dd-bd0b-0800200c9a66").tokenValiditySeconds(1209600)
                .and()
                    .logout()
                        .logoutUrl(env.getProperty("agile.security.login_out_url"))
                        .logoutSuccessUrl(env.getProperty("agile.security.login_out_success_url"))
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler(simpleUrlLogoutSuccessHandler())
                        .permitAll()//退出
                .and().exceptionHandling().accessDeniedPage(env.getProperty("agile.security.access_denied"))//权限不足跳页
                .and()
                   .sessionManagement()
                        .invalidSessionUrl(env.getProperty("agile.security.invalid_session_url")).maximumSessions(1).expiredUrl(env.getProperty("agile.security.expired_session_url")).sessionRegistry(sessionRegistry())//Session

        ;
    }



    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers(env.getProperty("agile.security.not_login_url"), env.getProperty("agile.security.invalid_session_url"),"/druid/**", "/swagger**","/verification");
    }

    @Override
    protected AuthenticationManager authenticationManager(){
        ProviderManager authenticationManager = new ProviderManager(Collections.singletonList(securityAuthenticationProvider));
        //不擦除认证密码，擦除会导致TokenBasedRememberMeServices因为找不到Credentials再调用UserDetailsService而抛出UsernameNotFoundException
        authenticationManager.setEraseCredentialsAfterAuthentication(false);
        return authenticationManager;
    }

    @Bean
    LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint(){
        return new LoginUrlAuthenticationEntryPoint(env.getProperty("agile.security.not_login_url"));
    }

    @Bean
    SecurityAuthenticationFilter securityAuthenticationFilter() throws Exception {
        SecurityAuthenticationFilter filter = new SecurityAuthenticationFilter();
        //切入登陆过滤链路地址
        filter.setFilterProcessesUrl(env.getProperty("agile.security.login_url"));

        //成功处理链路
        savedRequestAwareAuthenticationSuccessHandler().setAlwaysUseDefaultTargetUrl(true);
        savedRequestAwareAuthenticationSuccessHandler().setDefaultTargetUrl(env.getProperty("agile.security.success_url"));
        filter.setAuthenticationSuccessHandler(savedRequestAwareAuthenticationSuccessHandler());

        //失败处理链路
        simpleUrlAuthenticationFailureHandler().setDefaultFailureUrl(env.getProperty("agile.security.failure_url"));
        filter.setAuthenticationFailureHandler(simpleUrlAuthenticationFailureHandler());

        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler(){
        return new SavedRequestAwareAuthenticationSuccessHandler();
    }

    @Bean
    SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler(){
        return new SimpleUrlAuthenticationFailureHandler();
    }

    @Bean
    SimpleUrlLogoutSuccessHandler simpleUrlLogoutSuccessHandler(){
        return new SimpleUrlLogoutSuccessHandler();
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserDetailsService).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:com.agile.configure.agile-message.properties");
        messageSource.setDefaultEncoding("utf-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setCacheSeconds(10);
        return messageSource;
    }

    @Override
    public void setEnvironment(@NotNull Environment environment) {
        env = environment;
    }
}
