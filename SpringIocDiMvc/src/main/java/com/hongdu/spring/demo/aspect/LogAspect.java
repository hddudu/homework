package com.hongdu.spring.demo.aspect;

import com.hongdu.spring.aop.aspect.HdJoinPoint;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @ClassName LogAspect
 * @Description 日志切面 : 监听配置的地方， 然后切入这个类的属性
 * @Author dudu
 * @Date 2019/7/18 15:38
 * @Version 1.0
 */
@Slf4j
public class LogAspect {

    public void before(HdJoinPoint joinPoint) {
        //记录对象的 开始时间
        joinPoint.setUserAttribute("startTime_" + joinPoint.getMethod().getName(),System.currentTimeMillis());
        //这个方法中的逻辑，是由我们自己写的
        log.info("Invoker Before Method!!!" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArgs()));
    }

    public void after(HdJoinPoint joinPoint) {
        //记录对象的 开始时间
        //监测出方法的执行性能
        log.info("Invoker After Method!!!" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArgs()));
        long startTime = (Long) joinPoint.getUserAttribute("startTime_" + joinPoint.getMethod().getName());
        long endTime = System.currentTimeMillis();
        System.out.println("use time :" + (endTime - startTime));

    }

    public void afterThrowing(HdJoinPoint joinPoint, Throwable ex) {
        //异常检测， 我可以拿到异常的信息
        log.info("出现异常" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArgs()) +
                "\nThrows:" + ex.getMessage());
    }

}
