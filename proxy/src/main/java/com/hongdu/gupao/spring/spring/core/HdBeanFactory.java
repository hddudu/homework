package com.hongdu.gupao.spring.spring.core;

/**
 * @ClassName HdBeanFactory
 * @Description抽象顶层接口 都是这么玩的！！！
 *  * 直接就get该对象
 *
 *  单例工厂的顶层设计
 * @Author dudu
 * @Date 2019/7/14 21:00
 * @Version 1.0
 */
public interface HdBeanFactory {

    /**
     * 根据BeanName从IOC容器中获取一个实例的bean
     *  多个容器 不方便管理
     *  单例模式
     *      是否延时加载：
     *          1： 是
     *          2： 不是
     * 获取 bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object getBean(String beanName) throws Exception;

    /**
     * 根据beanClass获取 bean
     * @param beanClass
     * @return
     * @throws Exception
     */
    Object getBean(Class<?> beanClass) throws Exception;
}
