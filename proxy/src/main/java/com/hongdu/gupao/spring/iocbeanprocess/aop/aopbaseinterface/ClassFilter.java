package com.hongdu.gupao.spring.iocbeanprocess.aop.aopbaseinterface;

/**
 * @ClassName ClassFilter
 * @Description TODO
 * Pointcut	切点对象可以获取一个 ClassFilter 对象和一个 MethodMatcher 对象。
 *  前者用于判断是否对某个对象进行拦截
 *      （用于 筛选要代理的目标对象），
 *  后者用于判断是否对某个方法进行拦截
 *      （用于 在代理对象中对不同的方法进行不同的操作）。
 * @Author dudu
 * @Date 2019/7/12 14:17
 * @Version 1.0
 */
public interface ClassFilter {

    /**
     * 判断是否要切入的目标对象 类 对象
     * @param targetClass
     * @return
     */
    boolean matches(Class targetClass);
}
