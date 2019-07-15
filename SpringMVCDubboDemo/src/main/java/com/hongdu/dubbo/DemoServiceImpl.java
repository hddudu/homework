package com.hongdu.dubbo;

import com.hongdu.inter.DemoService;

/**
 * @ClassName DemoServiceImpl
 * @Description 暴露的服务实现
 * @Author dudu
 * @Date 2019/7/14 17:10
 * @Version 1.0
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        System.out.println("hello:"+name);
        return "hello,"+name;
    }
}
