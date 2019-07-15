package com.hongdu.gupao.spring.spring.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HdComponent {
    String value() default "";
}
