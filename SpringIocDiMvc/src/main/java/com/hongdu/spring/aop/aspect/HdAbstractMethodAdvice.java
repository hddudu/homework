package com.hongdu.spring.aop.aspect;

import java.lang.reflect.Method;

/**
 * @ClassName HdAbstractMethodAdvice
 * @Description
 * @Author dudu
 * @Date 2019/7/18 21:35
 * @Version 1.0
 */
public abstract class HdAbstractMethodAdvice{

    private Method aspectMethod;
    private Object aspectTarget;

    public HdAbstractMethodAdvice(Method aspectMethod, Object aspectTarget) {
        this.aspectMethod = aspectMethod;
        this.aspectTarget = aspectTarget;
    }
}
