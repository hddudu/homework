package com.hongdu.gupao.spring.iocbeanprocess.aop.aopbaseinterface;

/**
 * @ClassName PointcutAdvisor
 * @Description
 *  PointcutAdvisor	切点通知器，用于提供 对哪个对象的哪个方法进行什么样的拦截 的具体内容。
 *  通过它可以获取一个切点对象 Pointcut 和一个通知器对象 Advisor。
 *  Advisor :如何获取呢？
 *      通过继承Advisor （Advisor是继承aop框架中Advice）
 * @Author dudu
 * @Date 2019/7/12 14:46
 * @Version 1.0
 */
public interface PointcutAdvisor extends Advisor{

    /**
     * 获取 切点对象
     * @return
     */
    Pointcut getPointcut();

}
