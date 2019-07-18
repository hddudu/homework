package com.hongdu.gupao.chain.annotation;

import java.lang.annotation.*;

/**
 * 固定的价格注解 ：
 * 　两个简单的价格策略算法的实现，这里利用注解的两个属性：min 和 max 属性，
 *      定义策略类的使用要求：即计算的价格必须在指定的价格区间内
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FixedPrivPrice {
    /**
     * 最小值 默认0
     * @return
     */
    double min() default 0;

    /**
     * 最大值
     * @return
     */
    double max() default Integer.MAX_VALUE;

}
