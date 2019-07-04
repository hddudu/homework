package com.hongdu.gupao.spring.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@HdIndexed
public @interface HdComponent {
    /**
     * 默认值为空
     * @return
     */
    String value() default "";
}
