package com.hongdu.gupao.spring.spring.beans.config;

import lombok.Data;

/**
 * @ClassName HdBeanDefiniton
 * @Description beanDefinitinon定义 ： bean的类名字 + factoryBeanNanme + 是否延迟加载
 * @Author dudu
 * @Date 2019/7/14 21:18
 * @Version 1.0
 */
@Data
public class HdBeanDefiniton {
    //lei 对象 类名 类name
    //另一个版本：
    /**
     *      * bean实例
     *
    private Object bean;
      *      * bean名字 ： bean的别名 或者本名
             *
    private Class beanClass;
     *
      *      * bean类名
    private String beanClassName;
     */
    //bean类名
    private String beanClassName;
    //是否延迟加载
    private boolean lazyInit = false;
    //factoryBeanName 工厂bean名称
    private String factoryBeanName;
}
