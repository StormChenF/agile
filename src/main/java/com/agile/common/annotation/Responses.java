package com.agile.common.annotation;

import java.lang.annotation.*;

/**
 * Created by 佟盟 on 2018/2/7
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Responses {
    String code();
    String description();
    Class schema() default Object.class;
    boolean isArray() default false;
}
