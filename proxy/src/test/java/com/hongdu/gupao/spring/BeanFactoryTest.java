package com.hongdu.gupao.spring;

import com.hongdu.gupao.spring.iocbeanprocess.beans.BeanDefinition;
import com.hongdu.gupao.spring.iocbeanprocess.beans.factory.AbstractBeanFactory;
import com.hongdu.gupao.spring.iocbeanprocess.beans.factory.AutowireCapableBeanFactory;
import com.hongdu.gupao.spring.iocbeanprocess.beans.io.ResourceLoader;
import com.hongdu.gupao.spring.iocbeanprocess.beans.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;

/**
 * @author yihua.huang@dianping.com
 */
public class BeanFactoryTest {

    @Test
    public void testLazy() throws Exception {
        // 1.读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");

        // 2.初始化BeanFactory并注册bean
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registryBeanDefintion(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        // 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }

	@Test
	public void testPreInstantiate() throws Exception {
		// 1.读取配置
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");

		// 2.初始化BeanFactory并注册bean
		AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
		for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
			beanFactory.registryBeanDefintion(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
		}

        // 3.初始化bean
        beanFactory.preInstantiateSingleTons();

		// 4.获取bean
		HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
		helloWorldService.helloWorld();
	}
}
