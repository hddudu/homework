package com.hongdu.spring.annotation;

import java.lang.annotation.*;

/**
 *  请求参数
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HdRequestParam {

    /**
     * 空
     * @return
     */
    String value() default "";
}
