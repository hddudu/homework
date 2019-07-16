package com.hongdu.spring.beans.config.v1;

import lombok.Data;

/**
 * @ClassName HdBeanDefinition
 * @Description bean的 元数据定义
 *      beanClassName ： 对应的全路径名 --》 获得实体类  --》 反射获得对象
 *      factoryBeanName : 工厂bean名字 ---》 工厂bean的名字  --》 作为key
 *      isLazy ： 是否延迟加载
 *
 * @Author dudu
 * @Date 2019/7/16 15:01
 * @Version 1.0
 */
@Data
public class HdBeanDefinition {

    private String beanClassName;

    private boolean isLazy = false;

    private String factoryBeanName;

    public HdBeanDefinition() {
    }

    public HdBeanDefinition(String beanClassName, String factoryBeanName) {
        this.beanClassName = beanClassName;
        this.factoryBeanName = factoryBeanName;
    }
}
