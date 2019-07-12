package com.hongdu.gupao.spring.beans.xml;

import com.hongdu.gupao.spring.iocbeanprocess.beans.BeanDefinition;
import com.hongdu.gupao.spring.iocbeanprocess.beans.io.ResourceLoader;
import com.hongdu.gupao.spring.iocbeanprocess.beans.xml.XmlBeanDefinitionReader;
import com.hongdu.gupao.spring.iocbeanprocess.context.ClassPahXmlApplicationContexts;
import org.junit.Test;

import java.util.Map;

/**
 * @author yihua.huang@dianping.com
 */
public class XmlBeanDefinitionReaderTest {

	@Test
	public void test() throws Exception {
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
		Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
		printDefinitionMap(registry);
		/*

		 */

	}
	@Test
	public void test2() throws Exception {
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
		Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
		printDefinitionMap(registry);
		ClassPahXmlApplicationContexts xmlApplicationContext = new ClassPahXmlApplicationContexts("tinyioc.xml");

		xmlApplicationContext.refresh();
		System.out.println(xmlApplicationContext.getBeanFactory());
		/*

		 */

	}

	//遍历 map
	//遍历 BeanDefinition
	private void printDefinitionMap(Map<String, BeanDefinition> registry ) {
		for (Map.Entry<String, BeanDefinition> entry : registry.entrySet()) {
			System.out.println("字符串和 BeanDefinition对： " +  entry.getKey() + "\t = " +  entry.getValue().toString());
		}

	}


}
