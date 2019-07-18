package com.hongdu.spring.aop;

/**
 * @ClassName HdAopProxy
 * @Description 抽象代理顶层接口
 * @Author dudu
 * @Date 2019/7/18 15:36
 * @Version 1.0
 */
public interface HdAopProxy {
    /**
     * 获取aop ： 代理对象
     * @return
     * @throws Exception
     */
    Object getProxy() throws Exception;

    /**
     * 根据类加载器
     * 获取代理对象
     * @param classLoader
     * @return
     * @throws Exception
     */
    Object getProxy(ClassLoader classLoader)throws Exception;
}
