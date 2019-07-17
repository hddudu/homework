package com.hongdu.spring.mvc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName HdHandlerAdapter
 * @Description 适配器模式接口
 * @Author dudu
 * @Date 2019/7/17 10:16
 * @Version 1.0
 */
public interface HdHandlerAdapter {

    boolean supports(Object handler);


    HdModelAndViewV1 handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;


    long getLastModified(HttpServletRequest request, Object handler);
}
