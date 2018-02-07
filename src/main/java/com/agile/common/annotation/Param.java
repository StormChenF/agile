package com.agile.common.annotation;

import com.agile.common.base.Constant;

import java.lang.annotation.*;

/**
 * Created by 佟盟 on 2018/2/7
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Param {
    String name();
    String in();
    String description();
    boolean required() default false;
    Type type() default Type.STRING;
    enum Type{
        STRING,INTEGER,ARRAY
    }
}
