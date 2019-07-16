package com.hongdu.spring.annotation;

import java.lang.annotation.*;

/**
 * 派生 注解
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HdComponent {

    String value() default "";
}
