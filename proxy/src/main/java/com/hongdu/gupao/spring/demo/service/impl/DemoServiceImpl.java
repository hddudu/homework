package com.hongdu.gupao.spring.demo.service.impl;

import com.hongdu.gupao.spring.annotation.HdService;
import com.hongdu.gupao.spring.demo.service.IDemoService;

/**
 * @ClassName DemoServiceImpl
 * @Description 核心业务逻辑
 * @Author dudu
 * @Date 2019/7/5 19:47
 * @Version 1.0
 */
@HdService
public class DemoServiceImpl implements IDemoService {
    @Override
    public String get(String name) {
        return "My name is " + name;
    }
}
