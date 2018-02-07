package com.agile.common.annotation;

import java.lang.annotation.*;

/**
 * Created by 佟盟 on 2018/2/7
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface API {
    String name();
    Tag[] tag();
    Method[] method() default {Method.GET};
    String summary() default "";
    String description() default "";
    Param[] parameters() default {};
    Responses[] responses() default {};

    enum Method{
        GET,POST,DELETE,PUT
    }
}
