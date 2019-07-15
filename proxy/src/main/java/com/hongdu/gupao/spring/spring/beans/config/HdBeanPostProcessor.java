package com.hongdu.gupao.spring.spring.beans.config;

/**
 * @ClassName HdBeanPostProcessor
 * @Description 处理 前置 后置
 * @Author dudu
 * @Date 2019/7/15 12:57
 * @Version 1.0
 */
public class HdBeanPostProcessor {

    /***
     * 前置处理
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    /**
     * 后置处理 : 将对象进行后置包装
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

}
