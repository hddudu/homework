package com.hongdu.spring.annotation;

import java.lang.annotation.*;

/**
 * 控制器的注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HdController {

    /**
     * 默认值为空
     * @return
     */
    String value() default "";
}
