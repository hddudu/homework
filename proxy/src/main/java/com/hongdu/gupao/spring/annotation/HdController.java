package com.hongdu.gupao.spring.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@HdComponent
public @interface HdController {
    /**
     * 默认值为空串
     * @return
     */
    String value() default "";
}
