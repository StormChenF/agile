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
    private final HashSet<String> allowedMethods;
    private AgileCsrfSecurityRequestMatcher() {
        this.allowedMethods = new HashSet<>(Arrays.asList(new String[]{"GET", "HEAD", "TRACE", "OPTIONS"}));
    }

    /**
     * 需要排除的url列表
     */
    private String excludeUrl;

    @Override
    public boolean matches(HttpServletRequest httpServletRequest) {
        if (!StringUtil.isEmpty(excludeUrl)) {
            String servletPath = httpServletRequest.getServletPath();
            String[] excludeUrls = excludeUrl.split(",");
            for (String url:excludeUrls) {
                if (servletPath.contains(url)) {
                    return false;
                }
            }
        }
        return !this.allowedMethods.contains(httpServletRequest.getMethod());
    }

    public String getExcludeUrl() {
        return excludeUrl;
    }

    public void setExcludeUrl(String excludeUrl) {
        this.excludeUrl = excludeUrl;
    }

}
