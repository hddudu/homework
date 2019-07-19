package com.hongdu.spring.aop.aspect;

import com.hongdu.spring.aop.interceptor.HdMethodInterceptor;
import com.hongdu.spring.aop.invocations.HdMethodInvocation;

import java.lang.reflect.Method;

/**
 * @ClassName HdMethodBeforeAdvice
 * @Description 返回值处理 ：
 * @Author dudu
 * @Date 2019/7/18 21:25
 * @Version 1.0
 */
public class HdMethodAfterReturningAdvice extends HdAbstractMethodAdvice implements HdAdvice,HdMethodInterceptor {

    /**
     * 保存连接点
     */
    private HdJoinPoint joinPoint;
    /**
     * 传入 切点方法
     * @param aspectMethod
     */
    public HdMethodAfterReturningAdvice(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    private void afterThrowReturning(Object retValue,Method method, Object[] args, Object target) throws Throwable {
//        method.invoke(target, args);
//        super.invokeAdviceMethod(joinPoint, null, null);
        super.invokeAdviceMethod(this.joinPoint,retValue,null);
    }

    @Override
    public Object invoke(HdMethodInvocation mi) throws Throwable {
//        this.joinPoint = mi;
//        afterThrowReturning(mi.getMethod(), mi.getArgs(), mi.getThis());
//        return mi.proceed();
        this.joinPoint = mi;
        Object retVal = mi.proceed();
        this.afterThrowReturning(retVal, mi.getMethod(), mi.getArgs(), mi.getThis());
        return retVal;
    }
}
