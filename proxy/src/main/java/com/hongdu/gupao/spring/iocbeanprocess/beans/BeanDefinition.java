package com.hongdu.gupao.spring.iocbeanprocess.beans;

/**
 * @ClassName BeanDefinition
 * @Description 每一个bean元素的定义:
 *              bean : ---》 类实例
 *              beanClassName : -----》 类名字 必须有
 *              beanClass : 类对象 ---》 必须有
 *              类对象 类实例 类名字 ---》 这是一个bean的基础定义
 *              --->根据这个三个属性， 我们可以获得任何一个类的所有信息
 *
 *               bean的内容及元数据，保存在BeanFactory中，包装bean的实体
 * @Author dudu
 * @Date 2019/7/11 17:30
 * @Version 1.0
 */
public class BeanDefinition {
    /**
     * bean实例
     */
    private Object bean;

    /**
     * bean名字 ： bean的别名 或者本名
     */
    private Class beanClass;

    /**
     * bean类名
     */
    private String beanClassName;

    //两个形式 ： 比较类似
    // private List<PropertyValue> propertyValueList = new ArrayList<>();
    //采用下面的方式 ： 是因为可以进行逻辑处理
    private PropertyValues propertyValues = new PropertyValues();


    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public BeanDefinition() {
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "bean=" + bean +
                ", beanClass=" + beanClass +
                ", beanClassName='" + beanClassName + '\'' +
                ", propertyValues=" + propertyValues.toString() +
                '}';
    }
}
