package com.hongdu.gupao.spring.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@HdComponent
public @interface HdService {
    String value() default "";
}
