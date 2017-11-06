package com.agile.common.interceptor;

import org.hibernate.EmptyInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by 佟盟 on 2017/11/3
 */
@Component
public class JpaInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = -4455619920711458111L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String onPrepareStatement(String sql) {
        logger.info("[SQL语句:]"+sql);
        return super.onPrepareStatement(sql);
    }
}
