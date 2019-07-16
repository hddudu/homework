package com.hongdu.gupao.spring.spring.webmvc.servlet;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @ClassName HdHandlerMapping
 * @Description 处理  url 方法 控制器的映射
 * @Author dudu
 * @Date 2019/7/15 18:21
 * @Version 1.0
 */
@Data
public class HdHandlerMapping {

    /**
     * 保存方法对应的实例
     */
    private Object controller;
    /**
     * 保存映射的方法
     */
    private Method method;
    /**
     * URL的正则匹配
     */
    private Pattern pattern;

    public HdHandlerMapping() {
    }

    public HdHandlerMapping(Object controller, Method method, Pattern pattern) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
    }
}
