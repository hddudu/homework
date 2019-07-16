package com.hongdu.spring.core;

/**
 * @ClassName HdBeanFactory
 * @Description 顶层的 Bean 工厂定义
 *      Bean工厂和 factory的一个bean是不一样的
 * @Author dudu
 * @Date 2019/7/16 14:46
 * @Version 1.0
 */
public interface HdBeanFactory {

    //对FactoryBean的转义定义，因为如果使用bean的名字检索FactoryBean得到的对象是工厂生成的对象，
    //如果需要得到工厂本身，需要转义
    String FACTORY_BEAN_PREFIX = "&";


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
