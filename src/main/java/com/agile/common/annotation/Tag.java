package com.agile.common.annotation;

import java.lang.annotation.*;

/**
 * Created by 佟盟 on 2018/2/7
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Tag {
    String name();
    String description() default "";
}
