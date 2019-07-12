package com.hongdu.gupao.spring.iocbeanprocess.beans;

/**
 * @ClassName BeanDefinitionReader
 * @Description 从配置中读取 beanDefinition
 * @Author dudu
 * @Date 2019/7/11 19:00
 * @Version 1.0
 */
public interface BeanDefinitionReader {

    /**
     * 加载bean到容器map中 ： 待解析
     * @param location
     * @throws Exception
     */
    void loadBeanDefinitions(String location) throws  Exception;
}
