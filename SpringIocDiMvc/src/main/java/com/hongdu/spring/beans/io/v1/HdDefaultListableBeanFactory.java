package com.hongdu.spring.beans.io.v1;

import com.hongdu.spring.beans.config.v1.HdBeanDefinition;
import com.hongdu.spring.context.HdAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName HdDefaultListableBeanFactory
 * @Description 抽象类 继承抽象类 ： 好像没有什么意义
 *      子类继承抽象类 ： 作为一个中间类 ： 可以进行扩展
 * @Author dudu
 * @Date 2019/7/16 17:08
 * @Version 1.0
 */
public class HdDefaultListableBeanFactory extends HdAbstractApplicationContext {
    /**
     * 存储注册信息的BeanDefinition : final
     */
    protected final Map<String, HdBeanDefinition> beanDefinitonMap = new ConcurrentHashMap<String, HdBeanDefinition>();

}
