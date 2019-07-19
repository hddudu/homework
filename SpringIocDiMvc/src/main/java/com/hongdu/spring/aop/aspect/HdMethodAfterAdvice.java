package com.hongdu.spring.aop.aspect;

import com.hongdu.spring.aop.interceptor.HdMethodInterceptor;
import com.hongdu.spring.aop.invocations.HdMethodInvocation;

import java.lang.reflect.Method;

/**
 * @ClassName HdMethodAfterAdvice
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/18 21:26
 * @Version 1.0
 */
public class HdMethodAfterAdvice  extends HdAbstractMethodAdvice implements HdAdvice,HdMethodInterceptor {

    private HdJoinPoint joinPoint;

    /**
     * 传入 切点方法
     * @param aspectMethod
     */
    public HdMethodAfterAdvice(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    private void after(Method method, Object[] args, Object target) throws Exception {
        method.invoke(target, args);
    }

    @Override
    public Object invoke(HdMethodInvocation mi) throws Throwable {
        this.joinPoint = mi;
        after(mi.getMethod(), mi.getArgs(), mi.getThis());
        return mi.proceed();
    }
}
