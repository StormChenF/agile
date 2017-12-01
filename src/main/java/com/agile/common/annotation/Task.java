package com.agile.common.annotation;

import java.lang.annotation.*;

/**
 * Created by 佟盟 on 2017/11/29
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Task {
    String cron() default "";
    String name() default "";
    boolean sync() default true;
}
