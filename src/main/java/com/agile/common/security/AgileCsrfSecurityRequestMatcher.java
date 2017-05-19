package com.agile.common.security;

import com.agile.common.util.StringUtil;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by mydeathtrial on 2017/3/8
 */
public class AgileCsrfSecurityRequestMatcher implements RequestMatcher {

    /**
     * 需要排除的请求类型
     */
    private String allowedMethod;

    /**
     * 需要排除的url列表
     */
    private String allowedUrl;

    @Override
    public boolean matches(HttpServletRequest httpServletRequest) {
        if (!StringUtil.isEmpty(allowedUrl)) {
            String servletPath = httpServletRequest.getServletPath();
            String[] allowedUrls = allowedUrl.split(",");
            for (int i = 0 ; i < allowedUrls.length ; i++) {
                if (servletPath.contains(allowedUrls[i])) {
                    return false;
                }
            }
        }
        if (!StringUtil.isEmpty(allowedMethod)) {
            String servletMethod = httpServletRequest.getMethod();
            String[] allowedMethods = allowedMethod.split(",");
            for (int i = 0 ; i < allowedMethods.length ; i++) {
                if (servletMethod.contains(allowedMethods[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setAllowedUrl(String allowedUrl) {
        this.allowedUrl = allowedUrl;
    }

    public void setAllowedMethod(String allowedMethod) {
        this.allowedMethod = allowedMethod;
    }

}
