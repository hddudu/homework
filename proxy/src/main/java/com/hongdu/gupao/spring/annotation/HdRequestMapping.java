package com.hongdu.gupao.spring.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@HdMapping
public @interface HdRequestMapping {

    String name() default "";

    String[] params() default {};

    String[] headers() default {};

    String[] consumes() default {};

    String[] produces() default {};

    HdRequestMethod[] method() default {};

    /**
     * 互为别名  : 是value的别名 path，  就是注解path
     * 默认值必须为空
     * @return
     */
//    @HdAliasFor("path")
//    String[] value() default {};
    String value() default "";

    /**
     * 互为别名  ： 是path的别名 value， 就是注解value
     * 默认值必须为空
     * @return
     */
//    @HdAliasFor("value")
//    String[] path() default {};
}
