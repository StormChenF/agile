package com.agile.common.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by tongmeng on 2017/1/13
 */
public class AgileAuthenticationProvider implements AuthenticationProvider {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private AgileUserDetailsService userDetailsService = null;

    public AgileAuthenticationProvider() {
        super();
    }
    /**
     * @param userDetailsService 用户详情
     */
    public AgileAuthenticationProvider(AgileUserDetailsService userDetailsService) {
        super();
        this.userDetailsService = userDetailsService;
    }

    public AgileUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(AgileUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * provider的authenticate()方法，用于登录验证
     */
    @SuppressWarnings("unchecked")
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 1. Check username and password
        try {
            doLogin(authentication);
        } catch (Exception e) {
            if (e instanceof AuthenticationException) {
                throw (AuthenticationException) e;
            }
            logger.error("failure to doLogin", e);
        }

        // 2. Get UserDetails
        UserDetails userDetails = null;
        try {
            userDetails = this.userDetailsService.loadUserByUsername(authentication.getName());
        } catch (Exception e) {
            if (e instanceof AuthenticationException) {
                throw (AuthenticationException) e;
            }
            logger.error("failure to get user detail", e);
        }
        // 3. Check and get all of admin roles and contexts.
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) userDetails.getAuthorities();
//        if (authorities != null && !authorities.isEmpty()) {
            AgileAuthenticationToken token = new AgileAuthenticationToken(authentication.getName(),
                    authentication.getCredentials(), authorities);
            token.setDetails(userDetails);
            return token;
//        }
//        throw new BadCredentialsException("没有分配权限");
    }
    private void doLogin(Authentication authentication) throws AuthenticationException {
    }
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return true;
    }
}