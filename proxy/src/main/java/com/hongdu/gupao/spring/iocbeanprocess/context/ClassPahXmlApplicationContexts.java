package com.hongdu.gupao.spring.iocbeanprocess.context;

import com.hongdu.gupao.spring.iocbeanprocess.beans.BeanDefinition;
import com.hongdu.gupao.spring.iocbeanprocess.beans.factory.AbstractBeanFactory;
import com.hongdu.gupao.spring.iocbeanprocess.beans.factory.AutowireCapableBeanFactory;
import com.hongdu.gupao.spring.iocbeanprocess.beans.io.ResourceLoader;
import com.hongdu.gupao.spring.iocbeanprocess.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @ClassName ClassPahXmlApplicationContext
 * @Description 从类路径加载资源的具体实现类
 * @Author dudu
 * @Date 2019/7/12 11:05
 * @Version 1.0
 */
public class ClassPahXmlApplicationContexts extends AbstractApplicationContext {

    //加载资源文件 : 默认在classpath路径下
    private String configLocation;

    public ClassPahXmlApplicationContexts(String configLocation) throws Exception {
        //默认自动装载 : 传入自动装载的 实例
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPahXmlApplicationContexts(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        //添加解析资源文件
        super(beanFactory);
        this.configLocation = configLocation;
        //继承了 AbstractApplicationContext后 ： 直接可以调用父类中的初始化逻辑
        refresh();
    }

    /**
     * 加载出 ： bean的定义，保存到 beanFactory 中
     *          beanFacory中有容器可以存储bean
     * 注意：在 该简易版本 的实现中，先用 BeanDefinitionReader 读取 BeanDefiniton 后，
     *
     *    1： 保存在内置的 registry （键值对为 String - BeanDefinition 的哈希表，  AbstractBeanDefinitionReader类
     * 	 2： ApplicationContext 把 BeanDefinitionReader 中 registry 的键值对一个个赋值给 BeanFactory 中保存的 beanDefinitionMap；
     * 	 3： 而在 Spring 的实现中，BeanDefinitionReader 直接操作 BeanDefinition
     * 	    它的 getRegistry() 获取的不是内置的 registry，而是 BeanFactory 的实例。如何实现呢？
     *
     * 	 4： 以 DefaultListableBeanFactory 为例，它实现了一个 BeanDefinitonRigistry 接口，
     * 	        该接口把 BeanDefinition 的 注册 、获取 等方法都暴露了出来，
     *          这样，BeanDefinitionReader 可以直接通过这些方法把 BeanDefiniton 直接加载到 BeanFactory 中去。
     *
     * @param beanFactory
     */
    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        // 从类路径加载xml文件中bean的定义并注册到AbstractBeanDefinitionReader的registry中去
        try {
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
            // 将加载出的bean定义从registry中注册到beanFactory中
            for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
                beanFactory.registryBeanDefintion(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
            }
        } catch (Exception e) {
            System.out.println("加载文件资源失败！");
        }
    }
}
