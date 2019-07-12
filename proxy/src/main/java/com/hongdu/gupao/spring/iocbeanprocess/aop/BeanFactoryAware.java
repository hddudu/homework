package com.hongdu.gupao.spring.iocbeanprocess.aop;

import com.hongdu.gupao.spring.iocbeanprocess.beans.factory.BeanFactory;

/**
 * @ClassName BeanFactoryAware
 * @Description beanFactoryAware 设置了 BeanFactory ： 所以它拥有了 BeanFactory的全部功能
 *                  实现该接口 ： 随便定义一个方法中的引用属性， 那么就具有了好多的功能；
 *                  实现该接口 ： 随便定义一个方法中的引用属性， 那么就具有了好多的功能；
 *                  实现该接口 ： 随便定义一个方法中的引用属性， 那么就具有了好多的功能；
 *                  实现该接口 ： 随便定义一个方法中的引用属性， 那么就具有了好多的功能；
 *                  实现该接口 ： 随便定义一个方法中的引用属性， 那么就具有了好多的功能；
 * @Author dudu
 * @Date 2019/7/12 9:54
 * @Version 1.0
 */
public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;

}
