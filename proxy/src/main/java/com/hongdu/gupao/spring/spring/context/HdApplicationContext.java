package com.hongdu.gupao.spring.spring.context;

import com.hongdu.gupao.spring.spring.annotation.HdAutowired;
import com.hongdu.gupao.spring.spring.annotation.HdController;
import com.hongdu.gupao.spring.spring.annotation.HdService;
import com.hongdu.gupao.spring.spring.core.HdBeanFactory;
import com.hongdu.gupao.spring.spring.beans.HdBeanWrapper;
import com.hongdu.gupao.spring.spring.beans.config.HdBeanDefiniton;
import com.hongdu.gupao.spring.spring.beans.config.HdBeanPostProcessor;
import com.hongdu.gupao.spring.spring.beans.support.HdBeanDefinitionReader;
import com.hongdu.gupao.spring.spring.beans.support.HdDefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ApplicationContext
 * @Description HdApplicationContext 是一个最底层的接口实现
 *      按之前的课程分析 ： IOC / DI  / AOP /
 * @Author dudu
 * @Date 2019/7/14 21:05
 * @Version 1.0
 */
public class HdApplicationContext extends HdDefaultListableBeanFactory implements HdBeanFactory {

    //配置文件路径 数组
    private String[] configLocations;
    //加载bean对象到内存的读取器 : 本质是流读取
    private HdBeanDefinitionReader reader;

    //IOC 容器 单例
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    //通用的IOC容器
    private Map<String, HdBeanWrapper> factoryBeanInstanceCaches = new ConcurrentHashMap<>();


    /**
     * 数组 路径
     * @param configLocations
     */
    public HdApplicationContext(String...configLocations) {
        this.configLocations = configLocations;
        try {
            //调用 ioc di 模板 过程
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取bean的名称:
     * 从这里开始 ： 通过读取HdBeanDefintion的信息
     * 然后通过反射机制创建一个对象并返回
     * Spring的做法是： 不会把最原始的对象放出去， 会用一个BeanWrapper来进行一次包装
     * 装饰器莫斯 ：
     * 1： 保留原来的oop关系
     * 2： 我需要对它进行扩展，增强（为后面的aop打基础）
     * @param beanName
     * @return
     * @throws Exception
     */
    @Override
    public Object getBean(String beanName) throws Exception {
        //**************第一次写***********************
        //1: 创建实例 : 初始化
        // 循环依赖的问题 ：
        // 鸡生蛋 蛋生鸡问题 ==》 那么就先初始化 ==》 然后就。。。。解决了
        //管那么多， 先把鸡造出来， 或者先把蛋造出来
//        instantiateBean(beanName, new HdBeanDefiniton());
        //2： 注入bean
//        populateBean(beanName, new HdBeanDefiniton(), new HdBeanWrapper());

        //**************第二次写*********************** : 获取已经注册的beanName
        HdBeanDefiniton beanDefiniton = this.beanDefinitonMap.get(beanName);
        //对象 bean
        Object instance = null;

        //这个逻辑还不严谨，自己可以去参考Spring源码
        //工厂模式 + 策略模式
        HdBeanPostProcessor postProcessor = new HdBeanPostProcessor();
        postProcessor.postProcessBeforeInitialization(instance, beanName);

        //初始化完成
        instance = instantiateBean(beanName, beanDefiniton);

        //3、把这个对象封装到BeanWrapper中
        HdBeanWrapper beanWrapper = new HdBeanWrapper(instance);

        //singletonObjects

        //factoryBeanInstanceCache

        //4、把BeanWrapper存到IOC容器里面
//        //1、初始化

//        //class A{ B b;}
//        //class B{ A a;}
//        //先有鸡还是先有蛋的问题，一个方法是搞不定的，要分两次

        //2、拿到BeanWraoper之后，把BeanWrapper保存到IOC容器中去
        this.factoryBeanInstanceCaches.put(beanName, beanWrapper);
        postProcessor.postProcessAfterInitialization(instance, beanName);

        //4： 注入
        populateBean(beanName, beanDefiniton, beanWrapper);

        return this.factoryBeanInstanceCaches.get(beanName).getWrappedInstance();
    }

    /**
     * 注入属性 :
     * @param beanName
     * @param hdBeanDefiniton
     * @param hdBeanWrapper
     */
    private void populateBean(String beanName, HdBeanDefiniton hdBeanDefiniton, HdBeanWrapper hdBeanWrapper) {
        Object instance = hdBeanWrapper.getWrappedInstance();

        Class<?> clazz = hdBeanWrapper.getWrappedClass();

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
//                if(null == this.factoryBeanInstanceCaches.get(autowiredName).getWrappedInstance()) {
//                    continue;
//                }
                field.set(instance, this.factoryBeanInstanceCaches.get(autowiredName).getWrappedInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public Object getBean(Class<?> beanClass) throws Exception {
        return getBean(beanClass.getName());
    }

    /**
     * 初始化实例
     * @param beanName
     * @param hdBeanDefiniton
     */
    private Object instantiateBean(String beanName, HdBeanDefiniton hdBeanDefiniton) {
        //1、拿到要实例化的对象的类名 : 就是类的全路径
        String className = hdBeanDefiniton.getBeanClassName();
        //2: 反射实例化 ， 得到一个对象
        Object instance = null;
        //这里一个逻辑是 ： 分为单例的 IOC容器 和 支持原型的容器
        try {
            //假设默认就是单例,细节暂且不考虑，先把主线拉通
            if(this.singletonObjects.containsKey(className)) {
                instance = this.singletonObjects.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                //将类名 以及 加注解的时候的key都添加了
                this.singletonObjects.put(className, instance);
                this.singletonObjects.put(hdBeanDefiniton.getFactoryBeanName(), instance);
            }

        } catch (Exception e ) {
            e.printStackTrace();
        }
        return instance;
    }



    /**
     * 这是一个模板方法 : 父类只做定义 ： 子类做实现
     */
    @Override
    protected void refresh() throws Exception {
        //1： 定位 定位配置文件 ： annotation + xml  + properties文件
        reader = new HdBeanDefinitionReader(configLocations);

        //2： 加载配置文件， 扫描相关的类， 把他们封装成 BeanDefinition
        List<HdBeanDefiniton> beanDefinitons = reader.loadBeanDefinitions();

        //3： 注册：ba把配置信息放到容器里面 ： （伪IOC 容器 ： 真实的IOC容器是 ： BeanWrapper）
        doRegisterBeanDefinitions(beanDefinitons);

        //4： 把不是延时加载的类，要提前初始化
        doAutowired();

    }

    //只处理 非延时加载的情况
    private void doAutowired() {
        for (Map.Entry<String, HdBeanDefiniton> entry : super.beanDefinitonMap.entrySet()) {
            String beanName = entry.getKey();
            //不是延迟加载的情况 ：
            if(!entry.getValue().isLazyInit()) {
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 注册 beanDefinition
     * @param beanDefinitons
     */
    private void doRegisterBeanDefinitions(List<HdBeanDefiniton> beanDefinitons) throws Exception {
//        第一个版本
//        for (HdBeanDefiniton beanDefiniton : beanDefinitons) {
//            //TODO : 如果有注解？？？
//            super.beanDefinitonMap.put(beanDefiniton.getFactoryBeanName(), beanDefiniton);
//        }
        for (HdBeanDefiniton beanDefinition: beanDefinitons) {
            if(super.beanDefinitonMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception("The “" + beanDefinition.getFactoryBeanName() + "” is exists!!");
            }
            super.beanDefinitonMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
        }
        //到这里为止，容器初始化完毕
    }

    public static void main(String[] args) throws Exception {
        //1: 加载配置
        HdApplicationContext context = new HdApplicationContext("application.properties");
        //2： 初始化 : 总之是在这个一键启动的方法里面调用了 定位 + 加载 的过程
        // 以及 doAutowired的过程 ： 自动注入 ：
        context.refresh();

        //3： 查看加载 注册结果
        System.out.println("====================================注册到map容器中的结果=======================");
        for (Map.Entry<String, HdBeanDefiniton> entry : context.beanDefinitonMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        /**
         * 扫描包的结果::::::
         * com.hongdu.gupao.spring.demo.DemoIntroduction,
         * com.hongdu.gupao.spring.demo.mvc.DemoAction,
         * com.hongdu.gupao.spring.demo.mvc.TwoAction,
         * com.hongdu.gupao.spring.demo.service.IDemoService,
         * com.hongdu.gupao.spring.demo.service.impl.DemoServiceImpl,
         */
        /**
         * System.out.println("====================================注册到IOC容器中的结果=======================");
         * HdBeanDefiniton(beanClassName=com.hongdu.gupao.spring.demo.DemoIntroduction, lazyInit=false, factoryBeanName=demoIntroduction)
         * HdBeanDefiniton(beanClassName=com.hongdu.gupao.spring.demo.mvc.DemoAction, lazyInit=false, factoryBeanName=demoAction)
         * HdBeanDefiniton(beanClassName=com.hongdu.gupao.spring.demo.mvc.TwoAction, lazyInit=false, factoryBeanName=twoAction)
         * HdBeanDefiniton(beanClassName=com.hongdu.gupao.spring.demo.service.impl.DemoServiceImpl, lazyInit=false, factoryBeanName=demoServiceImpl)
         */

        System.out.println("====================================获取单例模式对象 ： =======================");
        for (Map.Entry<String, Object> entry : context.singletonObjects.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        /**
         * 获取单例模式对象 ：
         * demoAction = com.hongdu.gupao.spring.demo.mvc.DemoAction@1be6f5c3
         * com.hongdu.gupao.spring.demo.mvc.TwoAction = com.hongdu.gupao.spring.demo.mvc.TwoAction@6b884d57
         * demoIntroduction = com.hongdu.gupao.spring.demo.DemoIntroduction@38af3868
         * twoAction = com.hongdu.gupao.spring.demo.mvc.TwoAction@6b884d57
         * com.hongdu.gupao.spring.demo.mvc.DemoAction = com.hongdu.gupao.spring.demo.mvc.DemoAction@1be6f5c3
         * com.hongdu.gupao.spring.demo.DemoIntroduction = com.hongdu.gupao.spring.demo.DemoIntroduction@38af3868
         * demoServiceImpl = com.hongdu.gupao.spring.demo.service.impl.DemoServiceImpl@77459877
         * com.hongdu.gupao.spring.demo.service.impl.DemoServiceImpl = com.hongdu.gupao.spring.demo.service.impl.DemoServiceImpl@77459877
         */

        System.out.println("====================================包装后的： 获取实例化对象 ： 真正开始创建对象newInstance的结果=======================");
        for (Map.Entry<String, HdBeanWrapper> entry : context.factoryBeanInstanceCaches.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        /**
         * 包装后的： 获取实例化对象 ： 真正开始创建对象newInstance的结果
         * demoAction = com.hongdu.gupao.spring.spring.beans.HdBeanWrapper@5b2133b1
         * demoIntroduction = com.hongdu.gupao.spring.spring.beans.HdBeanWrapper@72ea2f77
         * twoAction = com.hongdu.gupao.spring.spring.beans.HdBeanWrapper@33c7353a
         * demoServiceImpl = com.hongdu.gupao.spring.spring.beans.HdBeanWrapper@681a9515
         */


    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitonMap.keySet().toArray(new String[this.beanDefinitonMap.size()]);
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitonMap.size();
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }

}
