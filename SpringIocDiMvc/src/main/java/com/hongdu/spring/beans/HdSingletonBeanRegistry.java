package com.hongdu.spring.beans;

/**
 * @ClassName HdSingletonBeanRegistry
 * @Description 单例 bean  注册器具
 * @Author dudu
 * @Date 2019/7/16 14:54
 * @Version 1.0
 */
public interface HdSingletonBeanRegistry {

    //******************************* 增删改查 ：CRUD功能***********************

    /**
     * 注册 ： 单例的 beanName : 比如： userService
     *          实例对象 ： singletonObject
     * @param beanName
     * @param singletonObject
     */
    void registerSingleton(String beanName, Object singletonObject);

    /**
     * 根据 beanName 获取 对应的单例对象
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);

    /**
     * 是否包含 ： 判断是否有
     * @param beanName
     * @return
     */
    boolean containsSingleton(String beanName);

    //*******************************统计功能***********************
    int getSingletonCount();
    Object getSingletonMutex();

}
