package com.hongdu.spring.aop.aspect;

import com.hongdu.spring.aop.interceptor.HdMethodInterceptor;

import java.lang.reflect.Method;

/**
 * @ClassName HdMethodAfterAdvice
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/18 21:26
 * @Version 1.0
 */
public class HdMethodAfterAdvice  extends HdAbstractMethodAdvice implements HdMethodInterceptor {

    /**
     * 传入 切点方法
     * @param aspectMethod
     */
    public HdMethodAfterAdvice(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(HdMethodInterceptor invocation) throws Throwable {
        return null;
    }
}
