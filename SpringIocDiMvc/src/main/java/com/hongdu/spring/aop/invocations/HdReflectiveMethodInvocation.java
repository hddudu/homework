package com.hongdu.spring.aop.invocations;

import com.hongdu.spring.aop.interceptor.HdMethodInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.InterceptorAndDynamicMethodMatcher;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName HdReflectiveMethodInvocation
 * @Description 相当于 GpMethodInvocation
 * @Author dudu
 * @Date 2019/7/18 16:22
 * @Version 1.0
 */
public class HdReflectiveMethodInvocation implements HdMethodInvocation {

    //代理对象 newProxy
    private Object proxy;
    //方法名
    private Method method;
    //目标的 newInstance
    private Object target;
    //参数
    private Object[] arguments;
    //拦截器链
    private List<Object> interceptorsAndDynamicMethodMatchers;
    //类路径
    private Class<?> targetClass;

    //定义一个索引： 来记录当前拦截器执行的位置
    /**
     * Index from 0 of the current interceptor we're invoking.
     * -1 until we invoke: then the current interceptor.
     */
    private int currentInterceptorIndex = -1;

    /**
     *
     * @param proxy Proxy.newProxyInstance(classLoader,
     *                                     this.advised.getTargetClass().getInterfaces(),
     *                                     this);
     * @param target clazz(被代理的类).newInstance
     * @param method 代理的方法 ： 被代理类中的方法
     * @param arguments 参数
     * @param targetClass  类 被代理的类路径：
     *                      String className = definition.getBeanClassName();
     *                      Class<?> clazz = Class.forName(className);
     *                      config.setTargetClass(clazz);
     * @param interceptorsAndDynamicMethodMatchers 拦截器链
     */
    public HdReflectiveMethodInvocation(
            Object proxy,  Object target, Method method,  Object[] arguments,
             Class<?> targetClass, List<Object> interceptorsAndDynamicMethodMatchers) {

        this.proxy = proxy;
        this.target = target;
        this.targetClass = targetClass;
        this.method = method;
        this.arguments = arguments;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }

    /**
     * 这方法 是一个连接点 接口上面的方法 ：
     *  是一个迭代器链条
     * @return
     * @throws Throwable
     */
    public Object proceed() throws Throwable {
//	We start with an index of -1 and increment early.
        //如果Interceptor执行完了，则执行 0
        if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
            return this.method.invoke(this.target, this.arguments);
        }

        //获取到下一个拦截器类 ：
        Object interceptorOrInterceptionAdvice =
                this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
        //如果要动态匹配joinPoint
        if (interceptorOrInterceptionAdvice instanceof HdMethodInterceptor) {
            // Evaluate dynamic method matcher here: static part will already have
            // been evaluated and found to match.
            HdMethodInterceptor mi =
                    (HdMethodInterceptor) interceptorOrInterceptionAdvice;
            //动态匹配：运行时参数是否满足匹配条件

                return dm.interceptor.invoke(this);

                // Dynamic matching failed.
                // Skip this interceptor and invoke the next in the chain.
                //动态匹配失败时,略过当前Intercetpor,调用下一个Interceptor
                return proceed();

        }
        else {
            // It's an interceptor, so we just invoke it: The pointcut will have
            // been evaluated statically before this object was constructed.
            //执行当前Intercetpor
            return ((MethodInterceptor) interceptorOrInterceptionAdvice).invoke(this);
        }
    }

    @Override
    public Method getMethod() {
        return null;
    }
}
