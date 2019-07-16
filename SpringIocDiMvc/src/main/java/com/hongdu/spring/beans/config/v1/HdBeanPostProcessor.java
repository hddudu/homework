package com.hongdu.spring.beans.config.v1;

import org.springframework.beans.BeansException;

/**
 * @ClassName HdBeanPostProcessor
 * @Description 初始化bena的前后
 *                      回调入口
 * @Author dudu
 * @Date 2019/7/16 15:49
 * @Version 1.0
 */
public class HdBeanPostProcessor {

    /**
     *  为在Bean的初始化前提供回调入口
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 为在Bean的初始化之后提供回调入口
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
