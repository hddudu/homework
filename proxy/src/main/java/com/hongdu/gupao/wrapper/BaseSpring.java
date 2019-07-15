package com.hongdu.gupao.wrapper;

import lombok.Data;

/**
 * @ClassName BaseSpring
 * @Description spring为原装对象
 * @Author dudu
 * @Date 2019/7/15 13:23
 * @Version 1.0
 */
@Data
public class BaseSpring {
    /**
     * 面向对象
     */
    private String oop;
    /**
     * ioc容器
     */
    private String ioc;
    /**
     * 依赖注入
     */
    private String di;
    /**
     * 面向切面
     */
    private String aop;
}
