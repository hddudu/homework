package com.hongdu.spring.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName HdApplicationContextAware
 * @Description 通过解耦的方式 获得 IOC容器的顶层设计
 *      后面将通过一个监听器去扫描所有的类， 只要实现了此接口，
 *              将自动调用 setApplicationContext（）方法， 从而将IOC容器 注入d到目标类中。
 * @Author dudu
 * @Date 2019/7/14 21:33
 * @Version 1.0
 */
public interface HdApplicationContextAware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
