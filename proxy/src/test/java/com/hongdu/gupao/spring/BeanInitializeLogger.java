package com.hongdu.gupao.spring;

import com.hongdu.gupao.spring.iocbeanprocess.beans.BeanPostProcessor;

/**
 * @author yihua.huang@dianping.com
 */
public class BeanInitializeLogger implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
		System.out.println("切入开始-------------------------");
		System.out.println("Initialize bean " + beanName + " start!");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
		System.out.println("Initialize bean " + beanName + " end!");
		System.out.println("切入结束-------------------------");
		return bean;
	}
}
