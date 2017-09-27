package com.agile.common.listener;

import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.annotation.WebListener;

/**
 * Created by 佟盟 on 2017/9/25
 * 配合spring security使用
 */
@WebListener
public class SessionEventListener extends HttpSessionEventPublisher {
}
