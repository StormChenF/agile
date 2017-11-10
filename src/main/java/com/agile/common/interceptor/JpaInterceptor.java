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
    private Logger logger = com.agile.common.config.LoggerFactory.createLogger("sql",JpaInterceptor.class);

    @Override
    public String onPrepareStatement(String sql) {
        logger.info("\n[SQL语句:]"+sql+"\n");
        return super.onPrepareStatement(sql);
    }
}
