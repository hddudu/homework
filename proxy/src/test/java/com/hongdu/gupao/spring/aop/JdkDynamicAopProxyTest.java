package com.hongdu.gupao.spring.aop;

import com.hongdu.gupao.spring.HelloWorldService;
import com.hongdu.gupao.spring.iocbeanprocess.aop.AdvisedSupport;
import com.hongdu.gupao.spring.iocbeanprocess.aop.JdkDynamicAopProxy;
import com.hongdu.gupao.spring.iocbeanprocess.aop.TargetSource;
import com.hongdu.gupao.spring.iocbeanprocess.context.ApplicationContext;
import com.hongdu.gupao.spring.iocbeanprocess.context.ClassPahXmlApplicationContexts;
import org.junit.Test;

/**
 * @author yihua.huang@dianping.com
 */
public class JdkDynamicAopProxyTest {

	@Test
	public void testInterceptor() throws Exception {
		// --------- helloWorldService without AOP
		ApplicationContext applicationContext = new ClassPahXmlApplicationContexts("tinyioc.xml");
		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
		helloWorldService.helloWorld();

		// --------- helloWorldService with AOP
		// 1. 设置被代理对象(Joinpoint)
		AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloWorldService,
                helloWorldService.getClass(),
                helloWorldService.getClass().getInterfaces());
		advisedSupport.setTargetSource(targetSource);

		// 2. 设置拦截器(Advice)
		TimerInterceptor timerInterceptor = new TimerInterceptor();
		advisedSupport.setMethodInterceptor(timerInterceptor);

		// 3. 创建代理(Proxy)
		JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
		HelloWorldService helloWorldServiceProxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();

		// 4. 基于AOP的调用
		helloWorldServiceProxy.helloWorld();

	}
}
