package com.hongdu.gupao.spring.spring.annotation;


import java.lang.annotation.*;

/**
 * 注解在 类或者方法上
 * ElementType.TYPE ： 表示类或者接口
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HdRequestMapping {
    String value() default "";
}
