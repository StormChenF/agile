package com.agile.common.listener;

import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.annotation.WebListener;

/**
 * Created by 佟盟 on 2017/9/25
 * 防止内存泄漏
 */
@WebListener
public class CleanupListener extends IntrospectorCleanupListener {
}
