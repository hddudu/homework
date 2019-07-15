package com.hongdu.gupao.spring.spring.annotation;

import java.lang.annotation.*;

/**
 *   Formal parameter declaration
 *P     ARAMETER,
 *      格式化声明的参数
 *      请求参数映射
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HdRequestParam {
    String value() default "";

    boolean required() default true;
}
