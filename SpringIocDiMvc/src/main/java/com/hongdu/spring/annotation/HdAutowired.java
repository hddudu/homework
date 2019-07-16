package com.hongdu.spring.annotation;

import java.lang.annotation.*;

/**
 * 自动注入 只能加到字段上面
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HdAutowired {
    /**
     * 默认值为空
     * @return
     */
    String value() default "";
}
