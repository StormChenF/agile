package com.agile.common.annotation;

import java.lang.annotation.*;

/**
 * Created by 佟盟 on 2017/12/14
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TaskTarget {
    String name() default "";
    String remark() default "";
}
