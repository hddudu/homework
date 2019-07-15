package com.hongdu.controller;

import com.hongdu.inter.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName TestController
 * @Description 只有一个Controller
 * @Author dudu
 * @Date 2019/7/14 17:20
 * @Version 1.0
 */
@Controller
public class TestController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("test")
    public String test(String name, HttpServletRequest request){
        System.out.println("MVC WELCOME");
        System.out.println(demoService.sayHello(name));
        request.setAttribute("hello",demoService.sayHello(name));
        return "test";
    }
}
