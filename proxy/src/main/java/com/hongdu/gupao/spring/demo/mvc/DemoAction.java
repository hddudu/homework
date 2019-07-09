package com.hongdu.gupao.spring.demo.mvc;

import com.hongdu.gupao.spring.annotation.HdAutowired;
import com.hongdu.gupao.spring.annotation.HdController;
import com.hongdu.gupao.spring.annotation.HdRequestMapping;
import com.hongdu.gupao.spring.annotation.HdRequestParam;
import com.hongdu.gupao.spring.demo.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @ClassName DemoAction
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/5 19:48
 * @Version 1.0
 */
@HdController
@HdRequestMapping( value = "/mvctest")
public class DemoAction {

    @HdAutowired
    private IDemoService demoService;

    @HdRequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response,
                      @HdRequestParam("name")String name) {
        String result = demoService.get(name);
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @HdRequestMapping("add")
    public void add(HttpServletRequest request, HttpServletResponse response,
                    @HdRequestParam("name")String name, @HdRequestParam("b")Integer b,  @HdRequestParam("a")Integer a) throws IOException {
        try {
            response.getWriter().write(a + "+" + b + "=" + (a + b));
        } catch (Exception e) {
            response.getWriter().write("500 Exception!" + Arrays.toString(e.getStackTrace()));
        }
    }

    @HdRequestMapping("add2")
    public void add2(HttpServletRequest request, HttpServletResponse response,
                    @HdRequestParam("name")String name) throws IOException {
        try {
            response.getWriter().write("我的名字  " + name);
        } catch (Exception e) {
            response.getWriter().write("500 Exception!" + Arrays.toString(e.getStackTrace()));
        }
    }

    @HdRequestMapping("/remove")
    public void remove(HttpServletRequest req,HttpServletResponse resp,
                       @HdRequestParam("id") Integer id){
    }

}
