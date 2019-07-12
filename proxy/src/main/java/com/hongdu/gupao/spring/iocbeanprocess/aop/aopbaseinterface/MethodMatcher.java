package com.hongdu.gupao.spring.iocbeanprocess.aop.aopbaseinterface;

import java.lang.reflect.Method;

/**
 * @ClassName MethodMatcher
 * @Description 方法拦截 ： 方法拦截器 ： 就是  切入点
 *
 *  Pointcut	切点对象可以获取一个 ClassFilter 对象和一个 MethodMatcher 对象。
 *  前者用于判断是否对某个对象进行拦截
 *      （用于 筛选要代理的目标对象），
 *  后者用于判断是否对某个方法进行拦截
 *      （用于 在代理对象中对不同的方法进行不同的操作）。
 *
 * @Author dudu
 * @Date 2019/7/12 14:15
 * @Version 1.0
 */
public interface MethodMatcher {

    /**
     * 判断方法 和目标类的方法是否匹配
     * @param method
     * @param targetClass
     * @return
     */
    boolean matches(Method method, Class targetClass);
}
