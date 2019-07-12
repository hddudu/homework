package com.hongdu.gupao.spring.iocbeanprocess.aop;

/**
 * @ClassName TargetSource
 * @Description 被代理的元数据
 * @Author dudu
 * @Date 2019/7/12 14:53
 * @Version 1.0
 */
public class TargetSource {

    /**
     * 代理的目标类
     */
    private Class<?> targetClass;

    /**
     * 代理 实现的接口
     */
    private Class<?>[] interfaces;

    //目标
    private Object target;



    public TargetSource(Object target, Class<?> targetClass, Class<?>[] interfaces) {
        this.targetClass = targetClass;
        this.interfaces = interfaces;
        this.target = target;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }

    public Object getTarget() {
        return target;
    }
}
