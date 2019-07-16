package com.hongdu.spring.core;

/**
 * @ClassName HdFactoryBean
 * @Description  工厂Bean，用于产生其他对象
 * @Author dudu
 * @Date 2019/7/16 14:47
 * @Version 1.0
 */
public interface HdFactoryBean {

    //Bean工厂创建的对象是否是单态模式，如果是单态模式，则整个容器中只有一个实例
    //对象，每次请求都返回同一个实例对象
    default boolean isSingleton() {
        return true;
    }

    /**
     * 获取 Bean 实例bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object getBean(String beanName) throws Exception;
}
