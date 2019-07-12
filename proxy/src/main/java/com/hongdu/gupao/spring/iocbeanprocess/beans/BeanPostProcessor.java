package com.hongdu.gupao.spring.iocbeanprocess.beans;

/**
 * @ClassName BeanPostProcessor
 * @Description  对bean的一些前置处理 和 后置处理
 * @Author dudu
 * @Date 2019/7/11 18:57
 * @Version 1.0
 */
public interface BeanPostProcessor {

    /**
     * bean初始化前的处理
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    /**
     * bean初始化后的 处理
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws  Exception;

}
