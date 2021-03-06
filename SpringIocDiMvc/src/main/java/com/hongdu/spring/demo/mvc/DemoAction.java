package com.hongdu.spring.demo.mvc;

import com.hongdu.spring.annotation.HdAutowired;
import com.hongdu.spring.annotation.HdController;
import com.hongdu.spring.annotation.HdRequestMapping;
import com.hongdu.spring.annotation.HdRequestParam;
import com.hongdu.spring.demo.service.IDemoService;

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
    //http://localhost:8282/proxy7/mvctest/query?name=hongdu
    @HdRequestMapping("/query*")
    public void query(HttpServletRequest request, HttpServletResponse response,
                      @HdRequestParam("name")String name) {
        String result = demoService.get(name);
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @HdRequestMapping("/query2")
    public void query2(HttpServletRequest request, HttpServletResponse response,
                      @HdRequestParam("name")String name) {
        String result = demoService.get(name);
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //http://localhost:8282/proxy7/mvctest/add?name=hongdu&a=1&b=1
    @HdRequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response,
                    @HdRequestParam("name")String name, @HdRequestParam("b")Integer b,  @HdRequestParam("a")Integer a) throws IOException {
        try {
            response.getWriter().write(a + "+" + b + "=" + (a + b));
        } catch (Exception e) {
            response.getWriter().write("500 Exception!" + Arrays.toString(e.getStackTrace()));
        }
    }

    @HdRequestMapping("/add2")
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
