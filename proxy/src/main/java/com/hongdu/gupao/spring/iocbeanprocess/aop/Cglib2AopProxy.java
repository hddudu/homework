package com.hongdu.gupao.spring.iocbeanprocess.aop;

import com.hongdu.gupao.spring.iocbeanprocess.aop.util.ReflectiveMethodInvocation;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName Cglib2AopProxy
 * @Description 继承了一个抽象类 ： 完成了接口的实现
 * @Author dudu
 * @Date 2019/7/12 15:10
 * @Version 1.0
 */
public class Cglib2AopProxy extends  AbstractAopProxy{

    public Cglib2AopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        //增强的父类
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTargetClass());
        //增强的接口
        enhancer.setInterfaces(advisedSupport.getTargetSource().getInterfaces());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advisedSupport));
        //创建代理对象 ： 字节码 ： 效率高 第一次编译慢
        Object enhanced = enhancer.create();
        return enhanced;
    }

    /**
     *
     */
    private class DynamicAdvisedInterceptor implements MethodInterceptor {

        //代理对象元数据
        private AdvisedSupport advised;

        //net.sf.cglib.proxy.MethodInterceptor
        //org.aopalliance.intercept.MethodInterceptor
        //上面的这个差别在哪里
        private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor;

        /**
         * 方法拦截器
         * @param advised
         */
        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
            this.delegateMethodInterceptor = advised.getMethodInterceptor();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            if(advised.getMethodMatcher() == null
                    || advised.getMethodMatcher().matches(method, advised.getTargetSource().getTargetClass())) {
                return delegateMethodInterceptor.invoke(new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, methodProxy));
            }
            return null;
        }
    }

    /**
     * Cglib 方法调用器
     */
    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
        private final MethodProxy methodProxy;
        public CglibMethodInvocation(Object object, Method method, Object[] args, MethodProxy methodProxy) {
            super(object, method, args);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.arguments);
        }
    }
}
