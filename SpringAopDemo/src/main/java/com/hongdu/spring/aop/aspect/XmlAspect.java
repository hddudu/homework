package com.hongdu.spring.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @ClassName XmlAspect
 * @Description
 *      XML版Aspect切面Bean(理解为TrsactionManager)
 * @Author dudu
 * @Date 2019/7/14 1:30
 * @Version 1.0
 */
public class XmlAspect {

    private final static Logger log = Logger.getLogger(XmlAspect.class);

    /*
     * 配置前置通知,使用在方法aspect()上注册的切入点
     * 同时接受JoinPoint切入点对象,可以没有该参数
     */
    public void before(JoinPoint joinPoint){
        System.out.println("开启数据库事务");
        System.out.println("开启日志记录");
        /*
[Ljava.lang.Object;@51650883
method-execution
Member com.hongdu.spring.aop.service.MemberService.get() 切入点 ： 方法名称
com.hongdu.spring.aop.service.MemberService@41200e0c ： 目标对象
com.hongdu.spring.aop.service.MemberService@41200e0c  ： 目标对象的之
         */
		System.out.println(joinPoint.getArgs()); //获取实参列表
		System.out.println(joinPoint.getKind());	//连接点类型，如method-execution
		System.out.println(joinPoint.getSignature()); //获取被调用的切点
		System.out.println(joinPoint.getTarget());	//获取目标对象
		System.out.println(joinPoint.getThis());	//获取this的值

        log.info("before " + joinPoint);
    }

    //配置后置通知,使用在方法aspect()上注册的切入点
    public void after(JoinPoint joinPoint){
        log.info("after " + joinPoint);
        System.out.println("关闭数据库事务");
        System.out.println("关闭日志记录");
    }

    //配置环绕通知,使用在方法aspect()上注册的切入点
    public void around(JoinPoint joinPoint){
        long start = System.currentTimeMillis();
        try {
            ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
        }
    }

    //配置后置返回通知,使用在方法aspect()上注册的切入点
    public void afterReturn(JoinPoint joinPoint){
        log.info("afterReturn " + joinPoint);
    }

    //配置抛出异常后通知,使用在方法aspect()上注册的切入点
    public void afterThrow(JoinPoint joinPoint, Exception ex){
        log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
    }

}
