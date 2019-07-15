package com.hongdu.gupao.spring.spring.annotation;

import java.lang.annotation.*;

/**
 * 控制器
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HdController {
    String value() default "";
}
