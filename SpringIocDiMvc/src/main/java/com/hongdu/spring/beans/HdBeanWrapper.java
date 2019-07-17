package com.hongdu.spring.beans;

import lombok.Data;

/**
 * @ClassName HdBeanWrapper
 * @Description bean的包装器
 * @Author dudu
 * @Date 2019/7/16 17:42
 * @Version 1.0
 */
@Data
public class HdBeanWrapper {

    //wrapperInstance ： 其实就是注入 或者 初始化的类的实例对象
    //也是注入属性时候需要获取的对象
    private Object wrapperInstance;
    private Class<?> wrapperClass;

    public HdBeanWrapper(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
//        this.wrapperClass = this.wrapperInstance.getClass();
    }

    public HdBeanWrapper(Object wrapperInstance, Class<?> wrapperClass) {
        this.wrapperInstance = wrapperInstance;
        this.wrapperClass = wrapperClass;
    }

    public HdBeanWrapper() {
    }

    public Object getWrapperInstance() {
        return this.wrapperInstance;
    }

    public Class<?> getWrapperClass() {
        return this.wrapperInstance.getClass();
    }
}
