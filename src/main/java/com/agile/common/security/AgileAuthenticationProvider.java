package com.agile.common.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by 佟盟 on 2017/1/13
 */
@Service
public class AgileAuthenticationProvider implements AuthenticationProvider {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AgileUserDetailsService userDetailsService;

    @Autowired
    public AgileAuthenticationProvider(AgileUserDetailsService userDetailsService) {
        super();
        this.userDetailsService = userDetailsService;
    }

    /**
     * provider的authenticate()方法，用于登录验证
     */
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 1. 校验用户名密码
        try {
            doLogin(authentication);
        } catch (Exception e) {
            if (e instanceof AuthenticationException) {
                throw (AuthenticationException) e;
            }
            logger.error("登陆失败", e);
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