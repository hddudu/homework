package com.hongdu.gupao.spring.spring.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HdComponent {
    String value() default "";
}
