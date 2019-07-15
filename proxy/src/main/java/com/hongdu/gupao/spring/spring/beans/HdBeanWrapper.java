package com.hongdu.gupao.spring.spring.beans;

/**
 * @ClassName HdBeanWrapper
 * @Description 包装对象 ： 比如代理对象 ： 来自代理类 ： 代理类 ： 有了aop后 ：
 *                      代理类 ： 其实是实现了所有方法 ： 持有代理的目标类的引用 ： 通过invoke 反射调用
 *                      aop ： 切面切入 编程的原理 ：
 *                          其实是 ： aop框架会把要切入的方法编译到了被切入的方法里面，
 *                                          相当于编译后的类 增加了实现方法 ：
 *                                                 只不过这个步骤是在aop框架中提供了实现以及支持的！
 * @Author dudu
 * @Date 2019/7/15 0:11
 * @Version 1.0
 */
public class HdBeanWrapper {
    /**
     * 包装后的实例
     */
    private Object wrapperInstance;

    /**
     * 包装的原来的类
     */
    private Class<?> wrappedClass;

    public HdBeanWrapper(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
    }

    /**
     * 获得包装的实例
     * @return
     */
    public Object getWrappedInstance() {
        return this.wrapperInstance;
    }

    /**
     * 获得包装类:  其实就是代理类了
     *   返回代理以后的Class
     *   可能会是这个 $Proxy0
     * @return
     */
    public Class<?> getWrappedClass() {
        return this.wrapperInstance.getClass();
    }

}
