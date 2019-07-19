package com.hongdu.spring.aop.aspect;

import com.hongdu.spring.aop.interceptor.HdMethodInterceptor;
import com.hongdu.spring.aop.invocations.HdReflectiveMethodInvocation;

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

    private void afterThrowReturning(Object retValue,Method method, Object[] args, Object target) throws Throwable {
//        method.invoke(target, args);
//        super.invokeAdviceMethod(joinPoint, null, null);
        super.invokeAdviceMethod(this.joinPoint,retValue,null);
    }

    @Override
    public Object invoke(HdReflectiveMethodInvocation mi) throws Throwable {
//        this.joinPoint = mi;
//        afterThrowReturning(mi.getMethod(), mi.getArgs(), mi.getThis());
//        return mi.proceed();
        Object retVal = mi.proceed();
        this.joinPoint = mi;
        this.afterThrowReturning(retVal, mi.getMethod(), mi.getArgs(), mi.getThis());
        return retVal;
    }
}
