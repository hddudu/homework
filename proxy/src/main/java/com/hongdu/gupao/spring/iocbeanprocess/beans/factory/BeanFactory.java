package com.hongdu.gupao.spring.iocbeanprocess.beans.factory;

/**
 * @ClassName BeanFactory
 * @Description bean的容器
 * @Author dudu
 * @Date 2019/7/12 9:10
 * @Version 1.0
 */
public interface BeanFactory {

    /**
     * 根据名称获取bean实例
     * @param name
     * @return
     * @throws Exception
     */
    Object getBean(String name) throws Exception;

}
