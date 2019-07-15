package com.hongdu.gupao.spring.spring.beans.support;

import com.hongdu.gupao.spring.spring.beans.config.HdBeanDefiniton;
import com.hongdu.gupao.spring.spring.context.HdAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName HdDefaultListableBeanFactory
 * @Description //存储注册信息的BeanDefinition
 * @Author dudu
 * @Date 2019/7/14 21:14
 * @Version 1.0
 */
public class HdDefaultListableBeanFactory extends HdAbstractApplicationContext {

    /**
     * 存储注册信息的BeanDefinition
     */
    protected final Map<String, HdBeanDefiniton> beanDefinitonMap = new ConcurrentHashMap<String, HdBeanDefiniton>();

    /**
     * 这里不有一个方法吗， 空实现 放这里吗？？？？
     * 思考 后 ： 决定放到一个抽象类中， 进行保护， 只提供给子类重写
     */
//    protected void refresh() {
//
//    }

}
