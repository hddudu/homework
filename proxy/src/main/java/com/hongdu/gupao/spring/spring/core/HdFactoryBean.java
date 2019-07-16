package com.hongdu.gupao.spring.spring.core;

/**
 * @ClassName HdFactoryBean
 * @Description 抽象顶层接口 都是这么玩的！！！
 * 直接就get该对象
 * @Author dudu
 * @Date 2019/7/14 21:01
 * @Version 1.0
 */

/**
 * 一般情况下，Spring通过反射机制利用<bean>的class属性指定实现类实例化Bean，在某些情况下，实例化Bean过程比较复杂，
 *
 * 如果按照传统的方式，则需要在<bean>中提供大量的配置信息。配置方式的灵活性是受限的，这时采用编码的方式可能会得到一个简单的方案。
 *
 * Spring为此提供了一个org.springframework.bean.factory.FactoryBean的工厂类接口，用户可以通过实现该接口定制实例化Bean的逻辑。
 *
 * FactoryBean接口对于Spring框架来说占用重要的地位，Spring自身就提供了70多个FactoryBean的实现。它们隐藏了实例化一些复杂Bean的细节，
 *
 * 给上层应用带来了便利。从Spring3.0开始，FactoryBean开始支持泛型，即接口声明改为FactoryBean<T>的形式
 *
 * 以Bean结尾，表示它是一个Bean，不同于普通Bean的是：它是实现了FactoryBean<T>接口的Bean，
 *
 * 根据该Bean的ID从BeanFactory中获取的实际上是FactoryBean的getObject()返回的对象，而不是FactoryBean本身，
 *
 * 如果要获取FactoryBean对象，请在id前面加一个&符号来获取。
 *
 * 　　例如自己实现一个FactoryBean，功能：用来代理一个对象，对该对象的所有方法做一个拦截，
 *  在调用前后都输出一行LOG，模仿ProxyFactoryBean的功能。
 *
 *
 * @param <T>
 */
//工厂Bean，用于产生其他对象
public interface HdFactoryBean<T> {


    /**
     * FactoryBean是一个接口，当在IOC容器中的Bean实现了FactoryBean后，
     * 通过getBean(String BeanName)获取到的Bean对象并不是FactoryBean的实现类对象，
     * 而是这个实现类中的getObject()方法返回的对象。要想获取FactoryBean的实现类，
     * 就要getBean(&BeanName)，在BeanName之前加上&。
     * @return
     * @throws Exception
     */
    //获取容器管理的对象实例
    T getObject() throws Exception;


    //获取Bean工厂创建的对象的类型
    Class<?> getObjectType();

    //Bean工厂创建的对象是否是单态模式，如果是单态模式，则整个容器中只有一个实例
    //对象，每次请求都返回同一个实例对象
    default boolean isSingleton() {
        return true;
    }
}
