package com.hongdu.spring.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HdService {

    /**
     *
     */
    String value() default "";
}
