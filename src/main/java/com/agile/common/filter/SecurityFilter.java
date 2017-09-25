package com.agile.common.filter;

import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.annotation.WebFilter;

/**
 * Created by 佟盟 on 2017/9/25
 */
@WebFilter(filterName = "springSecurityFilterChain",urlPatterns = "/*")
public class SecurityFilter extends DelegatingFilterProxy {
}
