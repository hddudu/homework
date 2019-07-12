package com.hongdu.gupao.spring.iocbeanprocess;

/**
 * @ClassName BeanReference
 * @Description 用于代表property标签的ref属性里的对象
 * @Author dudu
 * @Date 2019/7/11 17:27
 * @Version 1.0
 */
public class BeanReference {

    private String name;

    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
