package com.hongdu.gupao.spring.iocbeanprocess.beans.factory;

import com.hongdu.gupao.spring.iocbeanprocess.beans.BeanDefinition;
import com.hongdu.gupao.spring.iocbeanprocess.beans.BeanPostProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName AbstractBeanFactory
 * @Description 抽象 beanFactory 实现类 ：
 *          核心其实就是getBean方法
 *          核心其实就是getBean方法
 *          核心其实就是getBean方法
 *          核心其实就是getBean方法
 *          核心其实就是getBean方法
 *          核心其实就是getBean方法
 *          核心其实就是getBean方法
 *          核心其实就是getBean方法
 * @Author dudu
 * @Date 2019/7/12 9:11
 * @Version 1.0
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    /**
     * 多少个容器 ：  多少个容器的操作方法
     */

    //定义beanDefinition的容器
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    //添加 beanDefinitionNames
    private List<String> beanDefinitionNames = new ArrayList<>();

    //bean的前置或者后置处理的 beanProcessors 的 list
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    /**
     *  定义的bean
     *  1： 创建bean :
     *  2： 初始化bean
     *  3： 注入bean
     *  需要 ： bean容器 ： beanDefinition 的map
     *                      beanName  的 list
     *                      bean的前置或者后置处理的 beanProcessors 的 list
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if(beanDefinition == null) {
            throw new IllegalArgumentException("No bean named " + name + " is defined!");
        }
        //这个 bean 代表的是 类的实例 newInstance()
        Object bean = beanDefinition.getBean();
        if(bean == null) {
            bean = doCreateBean(beanDefinition);
            bean = initializeBean(bean, name);
            //再次重新设置 ： 前置处理 后置处理后的bean实例 ： 意思是 ： 增强后的bean
            //怎么个增强法呢？ 就是看实现 的前置处理时怎样的呐？ 应该是类似切面处理的格式
            beanDefinition.setBean(bean);
        }
        System.out.println(name + "::::" + bean);
        return bean;
    }

    //要取就要加 : 拿和注册 ： 新增或者删除 ： 弹出或者压入 ： 都是成对的操作
    public void registryBeanDefintion(String name, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
    }

    /**
     * 单例化 ： List集合中的所有beanName
     * @throws Exception
     */
    public void preInstantiateSingleTons() throws Exception {
        for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext();) {
            String beanName = (String) it.next();
            getBean(beanName);
        }
    }

    /**
     * 添加 增强的前置或者后置 处理
     * @param beanPostProcessor
     * @throws Exception
     */
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception {
        beanPostProcessorList.add(beanPostProcessor);
    }

    /**
     * 初始化 name 的 bean
     * 假如 name = UserController
     * 其实是在 bean的前后添加 ： 额外的处理 ：
     *
     *      额外添加的是 一系列的前置或者后置处理
     *
     * @param bean
     * @param name
     * @return
     */
    private Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }
        // TODO:call initialize method
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }
        return bean;
    }

    private Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        //注入该bean的原装实例对象 ： 如果是 User 就是 new User(); 但是其依赖的属性还未注入
        beanDefinition.setBean(bean);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    /**
     * 设置 ： 依赖属性
     *  依赖注入的方法 留给自动装配类实现
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

    }

    /**
     *  直接反射newInstance 实例
     * @param beanDefinition
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Object createBeanInstance(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException {
        return beanDefinition.getBeanClass().newInstance();
    }

    /**
     * 根据类 ： 类型 返回 所有的bean
     * @param type
     * @return
     * @throws Exception
     */
    public List getBeansForType(Class type) throws Exception {
        List beans = new ArrayList();
        for (String beanDefinitionName : beanDefinitionNames) {
            //A.class.isAssignableFrom(B.class) : 表明： A是B的接口或者父类
            //BeanPostProcessor.class 是beanMap中的接口或者父类
            //那么就说明 ： 有实现前置处理或者后置处理的类
            if(type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }
}
