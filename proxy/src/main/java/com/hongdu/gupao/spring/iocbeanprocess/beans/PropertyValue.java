package com.hongdu.gupao.spring.iocbeanprocess.beans;

/**
 * @ClassName PropertyValue
 * @Description 用于bean的属性注入
 * @Author dudu
 * @Date 2019/7/11 18:54
 * @Version 1.0
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
