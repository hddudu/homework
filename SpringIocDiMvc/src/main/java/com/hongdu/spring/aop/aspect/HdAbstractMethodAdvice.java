package com.hongdu.spring.aop.aspect;

import java.lang.reflect.Method;

/**
 * @ClassName HdAbstractMethodAdvice
 * @Description
 * @Author dudu
 * @Date 2019/7/18 21:35
 * @Version 1.0
 */
public abstract class HdAbstractMethodAdvice implements HdAdvice{

    private Method aspectMethod;
    private Object aspectTarget;

    public HdAbstractMethodAdvice(Method aspectMethod, Object aspectTarget) {
        this.aspectMethod = aspectMethod;
        this.aspectTarget = aspectTarget;
    }

    protected Object invokeAdviceMethod(HdJoinPoint joinPoint, Object returnValue, Throwable tx) throws Throwable {
        Class<?> [] paramTypes = this.aspectMethod.getParameterTypes();

        if(null == paramTypes || paramTypes.length == 0) {
            return this.aspectMethod.invoke(aspectTarget);
        } else {
            Object[] args = new Object[paramTypes.length];
            for (int i = 0, len = args.length; i < len; i ++) {
                if(paramTypes[i] == HdJoinPoint.class) {
                    args[i] = joinPoint;
                } else if(paramTypes[i] == Throwable.class) {
                    args[i] = tx;
                } else if(paramTypes[i] == Object.class) {
                    args[i] = returnValue;
                }
            }
            //参数进行了一次封装
            return this.aspectMethod.invoke(this.aspectTarget, args);
        }
    }
}
