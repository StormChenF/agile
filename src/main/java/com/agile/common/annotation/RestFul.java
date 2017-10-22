package com.agile.common.annotation;

import java.lang.annotation.*;

/**
 * Created by 佟盟 on 2017/10/18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestFul {
    String value();
}
