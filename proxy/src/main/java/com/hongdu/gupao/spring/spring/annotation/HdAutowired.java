package com.hongdu.gupao.spring.spring.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HdAutowired {
    String value() default "";
}
