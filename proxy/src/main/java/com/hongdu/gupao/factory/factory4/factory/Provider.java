package com.hongdu.gupao.factory.factory4.factory;

/**
 * @ClassName Provider
 * @Description 抽象创建工厂接口
 * @Author dudu
 * @Date 2019/7/19 11:51
 * @Version 1.0
 */
public interface Provider {
    /**
     *
     * @return
     */
    Sender produce();
}
