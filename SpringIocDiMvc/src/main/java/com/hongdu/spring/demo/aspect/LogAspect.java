package com.hongdu.spring.demo.aspect;

/**
 * @ClassName LogAspect
 * @Description 日志切面 : 监听配置的地方， 然后切入这个类的属性
 * @Author dudu
 * @Date 2019/7/18 15:38
 * @Version 1.0
 */
public class LogAspect {

    public void before() {
        //记录对象的 开始时间

    }

    public void after() {
        //记录对象的 开始时间
        //监测出方法的执行性能

    }

    public void afterThrowing() {
        //异常检测， 我可以拿到异常的信息
    }

}
