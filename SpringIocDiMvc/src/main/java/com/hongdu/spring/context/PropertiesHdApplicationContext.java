package com.hongdu.spring.context;

import com.hongdu.spring.annotation.HdAutowired;
import com.hongdu.spring.annotation.HdController;
import com.hongdu.spring.annotation.HdService;
import com.hongdu.spring.beans.HdBeanWrapper;
import com.hongdu.spring.beans.config.v1.HdBeanDefinition;
import com.hongdu.spring.beans.config.v1.HdBeanPostProcessor;
import com.hongdu.spring.beans.io.v1.HdBeanDefinitionReader;
import com.hongdu.spring.beans.io.v1.HdDefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName PropertiesHdApplicationContext
 * @Description 核心类 ： 入口类
 * 1： 初始化--》调用 读取加载 器
 *
 * 2：
 *
 * 3：
 *
 * @Author dudu
 * @Date 2019/7/16 17:30
 * @Version 1.0
 */
public class PropertiesHdApplicationContext extends HdDefaultListableBeanFactory implements HdApplicationContext {

    /**
     * newInstance 报错:
     * InstantiationException    →    没有无参构造，类名错误
     *
     * IllegalAccessException   →    构造方法私有化
     */
    public PropertiesHdApplicationContext() {
    }

    /**
     * 输入参数文件
     */
    private String[] configLocations;

    /**
     * 读取加载器
     */
    private HdBeanDefinitionReader reader;

    //通用的IOC容器
    private Map<String, HdBeanWrapper> factoryBeanInstanceCaches = new ConcurrentHashMap<>();
    //单例的ioc容器
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();


    public PropertiesHdApplicationContext(String...locations) {
        //初始化文件路径
        this.configLocations = locations;
        try {
            //调用 ioc di 模板 过程
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void refresh() throws Exception {
        //1: ioc : 加载 + load内存
        //一个参数入口 ： 全是处理以及返回 简单化
        reader = new HdBeanDefinitionReader(configLocations);
        List<HdBeanDefinition> beanDefinitons = reader.loadBeanDefinitions2();
        doRegisterBeanDefinitions(beanDefinitons);


        //2: di ： 注册 + 设置注入
        //4： 把不是延时加载的类，要提前初始化
        doAutowired();

    }

    private void doAutowired() {
        for (Map.Entry<String, HdBeanDefinition> entry : super.beanDefinitonMap.entrySet()) {
            String beanName = entry.getKey();
            //不是延迟加载的情况 ：
            if(!entry.getValue().isLazy()) {
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void doRegisterBeanDefinitions(List<HdBeanDefinition> beanDefinitons) throws Exception {
        for (HdBeanDefinition beanDefinition: beanDefinitons) {
            if(super.beanDefinitonMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception("The “" + beanDefinition.getFactoryBeanName() + "” is exists!!");
            }
            super.beanDefinitonMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
        }
    }

    @Override
    public Object getBean(String beanName) throws Exception {
        //前置处理 beanDefinitonMap ： bean + beanDefinition 对象
        HdBeanDefinition definition = super.beanDefinitonMap.get(beanName);
        Object instance = null;
        HdBeanPostProcessor postProcessor = new HdBeanPostProcessor();
        postProcessor.postProcessBeforeInitialization(instance, beanName);
        //初始化
        instance = instantiateBean(beanName, definition);
        //3、把这个对象封装到BeanWrapper中
        HdBeanWrapper beanWrapper = new HdBeanWrapper(instance);
        //后置处理
        postProcessor.postProcessAfterInitialization(instance, beanName);
        //包装对象 才是真正的ioc容器

        //4： 注入
        populateBean(beanName, definition, beanWrapper);
        this.factoryBeanInstanceCaches.put(beanName, beanWrapper);
        return this.factoryBeanInstanceCaches.get(beanName).getWrapperInstance();
    }

    private void populateBean(String beanName, HdBeanDefinition definition, HdBeanWrapper beanWrapper) {
        Object instance = beanWrapper.getWrapperInstance();

        Class<?> clazz = beanWrapper.getWrapperClass();

        //判断只有加了注解的类，才执行依赖注入
        if(!(clazz.isAnnotationPresent(HdController.class) || clazz.isAnnotationPresent(HdService.class))) {
            return;
        }
        //获得所有的 fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {

            //如果没有配置注解： 跳过
            if(!field.isAnnotationPresent(HdAutowired.class)) {continue;}
            HdAutowired autowired = field.getAnnotation(HdAutowired.class);
            String autowiredName = autowired.value().trim();
            if("".equals(autowiredName)) {
                //com.hongdu.spring.demo.DemoAction
                autowiredName = field.getType().getName();
            }
            field.setAccessible(true);

            //为什么会为NULL，先留个坑
            try {
                if(null == this.factoryBeanInstanceCaches.get(autowiredName)) {
                    continue;
                }
                field.set(instance, this.factoryBeanInstanceCaches.get(autowiredName).getWrapperInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private Object instantiateBean(String beanName, HdBeanDefinition definition) {
        //1、拿到要实例化的对象的类名 : 就是类的全路径 : 在存储 beanDefinitonMap的时候，存的beanClassName 会有接口路径， 所以不能实例化
        String className = definition.getBeanClassName();

        //2: 反射实例化 ， 得到一个对象
        Object instance = null;
        //这里一个逻辑是 ： 分为单例的 IOC容器 和 支持原型的容器
        try {
            //假设默认就是单例,细节暂且不考虑，先把主线拉通
            if(this.singletonObjects.containsKey(className)) {
                instance = this.singletonObjects.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                if(!(clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers()))) {
                    instance = clazz.newInstance();
                    //将类名 以及 加注解的时候的key都添加了
                    this.singletonObjects.put(className, instance);
                    this.singletonObjects.put(definition.getFactoryBeanName(), instance);
                } else {
                    className = definition.getFactoryBeanName();
                    clazz = Class.forName(className);
                    instance = clazz.newInstance();
                    //将类名 以及 加注解的时候的key都添加了
                    this.singletonObjects.put(className, instance);
                    this.singletonObjects.put(definition.getFactoryBeanName(), instance);
                }
            }

        } catch (Exception e ) {
            e.printStackTrace();
        }
        return instance;
    }

    @Override
    public Object getBean(Class<?> beanClass) throws Exception {
        return null;
    }
}
