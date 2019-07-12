package com.hongdu.gupao.spring.iocbeanprocess.aop;

import com.hongdu.gupao.spring.iocbeanprocess.aop.util.ReflectiveMethodInvocation;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName JdkDynamicAopProxy
 * @Description jdk 原生切入 方法逻辑
 * @Author dudu
 * @Date 2019/7/12 15:37
 * @Version 1.0
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(),
                        advisedSupport.getTargetSource().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
        if(advisedSupport.getMethodMatcher() != null
            && advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getTarget().getClass())) {
            //切入 ： 方法逻辑 ： 再调用反射方法
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget()
                    , method, args));
        }
        //直接调用反射
        return method.invoke(advisedSupport.getTargetSource().getTarget(), args);
    }
}
