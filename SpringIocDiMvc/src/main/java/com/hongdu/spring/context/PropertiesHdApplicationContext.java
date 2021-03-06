package com.hongdu.spring.context;

import com.hongdu.spring.annotation.HdAutowired;
import com.hongdu.spring.annotation.HdController;
import com.hongdu.spring.annotation.HdService;
import com.hongdu.spring.aop.HdAopProxy;
import com.hongdu.spring.aop.HdCglibAopProxy;
import com.hongdu.spring.aop.HdJdkDynamicAopProxy;
import com.hongdu.spring.aop.config.HdAopConfig;
import com.hongdu.spring.aop.support.HdAdvisedSupport;
import com.hongdu.spring.beans.HdBeanWrapper;
import com.hongdu.spring.beans.config.v1.HdBeanDefinition;
import com.hongdu.spring.beans.config.v1.HdBeanPostProcessor;
import com.hongdu.spring.beans.io.v1.HdBeanDefinitionReader;
import com.hongdu.spring.beans.io.v1.HdDefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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
//        List<HdBeanDefinition> beanDefinitons = reader.loadBeanDefinitions2();
        List<HdBeanDefinition> beanDefinitons = reader.loadBeanDefinition3();
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
        //介入通知器
        HdBeanPostProcessor postProcessor = new HdBeanPostProcessor();
        postProcessor.postProcessBeforeInitialization(instance, beanName);
        //初始化
        instance = instantiateBean(beanName, definition);
        //3、把这个对象封装到BeanWrapper中
        //aop 来添加逻辑：
        HdBeanWrapper beanWrapper = new HdBeanWrapper(instance);

        //创建一个 代理的策略， 看使用cglib还是 jdk


        //后置处理
        //2、拿到BeanWraoper之后，把BeanWrapper保存到IOC容器中去
        this.factoryBeanInstanceCaches.put(beanName, beanWrapper);
        postProcessor.postProcessAfterInitialization(instance, beanName);
        //包装对象 才是真正的ioc容器

        //错误的顺序： 需要先在 真实容器中添加 ： beanName 和 beanWrapper 才能进行注入 --》 否则注入会不成功过
        //为什么呢？ 因为注入在前面的话， 那么首先是往包装的对象里面注入了值， 然后， 才把包装对象放到真实的ioc容器中
        //4： 注入
        populateBean(beanName, definition, beanWrapper);
        //this.factoryBeanInstanceCaches.put(beanName, beanWrapper);
        return this.factoryBeanInstanceCaches.get(beanName).getWrapperInstance();
    }

    /**
     * 将原始类 做了包装是没有问题，
     *  但是注入的时候， 仍然注入的是原来的类的配置了自动注入的属性
     * @param beanName
     * @param definition
     * @param beanWrapper
     */
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
//                if(null == this.factoryBeanInstanceCaches.get(autowiredName)) {
//                    continue;
//                }
                //这个是接口的路径名
                if(this.factoryBeanInstanceCaches.get(autowiredName) == null){ continue; }
//                if(instance == null){
//                    continue;
//                }
                //huoqu到了接口的路径名 找到了对应的 包装的实例对象
                field.set(instance, this.factoryBeanInstanceCaches.get(autowiredName).getWrapperInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //HdBeanDefinition 里面注入的接口： 对用了beanClassName 是它的实现类
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
                //原始版本
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                HdAdvisedSupport config = instantionAopConfig(definition);
                config.setTargetClass(clazz);
                config.setTarget(instance);
                //如果符合我们的代理的规则， 那么就进行创建代理对象
                if(config.pointCutMatch()) {
                    HdAopProxy px = createProxy(config);
                    instance = px.getProxy();
//                    instance = createProxy(config).getProxy();
                }
                //将类名 以及 加注解的时候的key都添加了
                this.singletonObjects.put(className, instance);
                this.singletonObjects.put(definition.getFactoryBeanName(), instance);



                //第二版本
//                Class<?> clazz = Class.forName(className);
//                if(!(clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers()))) {
//                    instance = clazz.newInstance();
//                    //将类名 以及 加注解的时候的key都添加了
//                    this.singletonObjects.put(className, instance);
//                    this.singletonObjects.put(definition.getFactoryBeanName(), instance);
//                } else {
//                    //这个地方不对： 注入的应该是 ：
//                    className = definition.getFactoryBeanName();
//                    clazz = Class.forName(className);
//                    instance = clazz.newInstance();
//                    //将类名 以及 加注解的时候的key都添加了
//                    this.singletonObjects.put(className, instance);
//                    this.singletonObjects.put(definition.getFactoryBeanName(), instance);
//                }
            }

        } catch (Exception e ) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * 根据 jdk和cglib代理的方式 不 一样 进行不同的代理方式
     *  就是判断接口数是否大于0
     * @param config
     * @return
     */
    private HdAopProxy createProxy(HdAdvisedSupport config) {
        Class<?> targetClass = config.getTargetClass();
        if(targetClass.getInterfaces().length > 0) {
            return new HdJdkDynamicAopProxy(config);
        }
        return new HdCglibAopProxy(config);
    }

    /**
     * 初始化代理的对象 ：
     * @param definition
     * @return
     */
    private HdAdvisedSupport instantionAopConfig(HdBeanDefinition definition) {

        HdAopConfig config = new HdAopConfig();
        Properties pro = this.reader.getConfig();
        config.setPointCut(this.reader.getConfig().getProperty("pointCut"));
        config.setAspectBefore(pro.getProperty("aspectBefore"));
        config.setAspectAfter(pro.getProperty("aspectAfter"));
        config.setAspectClass(pro.getProperty("aspectClass"));
        config.setAspectAfterThrow(pro.getProperty("aspectAfterThrow"));
        config.setAspectAfterThrowingName(pro.getProperty("aspectAfterThrowingName"));
        HdAdvisedSupport advised = new HdAdvisedSupport();
        advised.setAopConfig(config);
        return new HdAdvisedSupport(config);
    }

    @Override
    public Object getBean(Class<?> beanClass) throws Exception {
        return getBean(beanClass.getSimpleName());
    }

    //*****最少知道原则 ----》 保证安全性
    //获取 map的 所有key  ：用来处理 注入的对象
    public String[] getBeanDefinitionNames() {
        return this.beanDefinitonMap.keySet().toArray(new String[beanDefinitonMap.size()]);
    }

    //获取config : 来自配置文件中的config
    public Properties getConfig() {
        return this.reader.getConfig();
    }



}
