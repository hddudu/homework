package com.hongdu.gupao.spring.iocbeanprocess.beans;

import com.hongdu.gupao.spring.iocbeanprocess.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AbstractBeanDefinitionReader
 * @Description beanDefinition的加载器 ： 将BeanDefinition读取到内存
 * 从配置中读取BeanDefinition
 * @Author dudu
 * @Date 2019/7/12 9:00
 * @Version 1.0
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    //读取存放容器 : 注册到了这个 内存容器
    private Map<String, BeanDefinition> registry;
    //读取器
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        registry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    /**
     * 实现接口可以先实现这个逻辑，
     * 继承的情况下，不建议修改父类的方法
     * @param location
     * @throws Exception
     */
    @Override
    public void loadBeanDefinitions(String location) throws Exception {

    }
}
