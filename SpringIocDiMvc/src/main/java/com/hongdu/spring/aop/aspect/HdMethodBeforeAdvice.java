package com.hongdu.spring.aop.aspect;

import com.hongdu.spring.aop.interceptor.HdMethodInterceptor;
import com.hongdu.spring.aop.invocations.HdReflectiveMethodInvocation;

import java.lang.reflect.Method;

/**
 * @ClassName HdMethodBeforeAdvice
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/18 21:25
 * @Version 1.0
 */
public class HdMethodBeforeAdvice extends HdAbstractMethodAdvice implements HdAdvice,HdMethodInterceptor {
    /**
     * 保存连接点
     */
    private HdJoinPoint joinPoint;
    /**
     * 传入 切点方法
     * @param aspectMethod
     */
    public HdMethodBeforeAdvice(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }
    /**
     * 反射调用
     * @param method
     * @param args
     * @param target
     * @throws Exception
     */
    private void before(Method method, Object[] args, Object target) throws Throwable {
//        method.invoke(target, args);
        super.invokeAdviceMethod(this.joinPoint,null,null);
    }
    @Override
    public Object invoke(HdReflectiveMethodInvocation mi) throws Throwable {
        this.joinPoint = mi;
        before(mi.getMethod(), mi.getArgs(), mi.getThis());
        return mi.proceed();
    }
}
