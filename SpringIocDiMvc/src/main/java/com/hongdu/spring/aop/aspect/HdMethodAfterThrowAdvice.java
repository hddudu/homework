package com.hongdu.spring.aop.aspect;

import com.hongdu.spring.aop.interceptor.HdMethodInterceptor;
import com.hongdu.spring.aop.invocations.HdReflectiveMethodInvocation;

import java.lang.reflect.Method;

/**
 * @ClassName HdMethodAfterThrowAdvice
 * @Description 异常处理
 * @Author dudu
 * @Date 2019/7/18 21:26
 * @Version 1.0
 */
public class HdMethodAfterThrowAdvice extends HdAbstractMethodAdvice  implements HdAdvice, HdMethodInterceptor {

    /**
     * 保存连接点
     */
    private HdJoinPoint joinPoint;

    /**
     * 异常名称
     */
    private String throwingName;

    /**
     * 传入 切点方法
     * @param aspectMethod
     */
    public HdMethodAfterThrowAdvice(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(HdReflectiveMethodInvocation mi) throws Throwable {
//        this.joinPoint = mi;
//        afterThrow(mi.getMethod(), mi.getArgs(), mi.getThis());
//        return mi.proceed();
        try {
            this.joinPoint = mi;
            return mi.proceed();
        } catch (Throwable tx) {
            super.invokeAdviceMethod(joinPoint,null, tx.getCause());
            //抛出 ： 类似 return 的功能
            throw tx;
        }
    }

    private void afterThrow(Method method, Object[] args, Object target) throws Exception {
        method.invoke(target, args);
    }

    /**
     * 抛出异常的名称需要捕获
     * @param throwName
     */
    public void setThrowName(String throwName) {
        this.throwingName = throwName;
    }

}
