package com.hongdu.gupao.spring.spring;

import com.hongdu.gupao.spring.demo.mvc.MyAction;
import com.hongdu.gupao.spring.spring.context.HdApplicationContext;

/**
 * Created by Tom on 2019/4/13.
 */
public class Test {

    public static void main(String[] args) {

        HdApplicationContext context = new HdApplicationContext("classpath:application.properties");
        try {
            Object object = context.getBean(MyAction.class);
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
