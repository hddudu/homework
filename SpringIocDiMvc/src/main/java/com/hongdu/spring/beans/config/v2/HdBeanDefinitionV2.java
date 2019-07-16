package com.hongdu.spring.beans.config.v2;

import lombok.Data;

/**
 * @ClassName HdBeanDefinitionV2
 * @Description 每一个bean元素的定义:
 *  *              bean : ---》 类实例
 *  *              beanClassName : -----》 类名字 必须有
 *  *              beanClass : 类对象 ---》 必须有
 *  *              类对象 类实例 类名字 ---》 这是一个bean的基础定义
 *  *              --->根据这个三个属性， 我们可以获得任何一个类的所有信息
 *  *
 *  *               bean的内容及元数据，保存在BeanFactory中，包装bean的实体
 * @Author dudu
 * @Date 2019/7/16 15:28
 * @Version 1.0
 */

@Data
public class HdBeanDefinitionV2 {

    private Object bean;
    private String beanClassName;
    private Class<?> beanClass;
    /**
     * 依赖注入的属性： 这里不加了
     */


}
