package com.agile.common.annotation;

import java.lang.annotation.*;

/**
 * Created by 佟盟 on 2018/1/5
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Properties {
    String prefix () default "";
}
