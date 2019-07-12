package com.hongdu.gupao.spring.iocbeanprocess.aop;

import com.hongdu.gupao.spring.iocbeanprocess.aop.aopbaseinterface.MethodMatcher;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @ClassName AdvisedSupport
 * @Description 代理支持 ：
 * @Author dudu
 * @Date 2019/7/12 14:13
 * @Version 1.0
 */
public class AdvisedSupport {

    /**
     * 被代理的类对象
     */
    private TargetSource targetSource;

    /**
     * 方法拦截器
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 方法匹配器
     */
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
