package com.hongdu.spring.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HdRequestMapping {
    /**
     * 默认为空
     * @return
     */
    String value() default "";
}
