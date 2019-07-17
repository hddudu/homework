package com.hongdu.spring;

import com.hongdu.spring.context.PropertiesHdApplicationContext;
import com.hongdu.spring.demo.mvc.MyAction;

/**
 * @ClassName Test
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/16 18:30
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        PropertiesHdApplicationContext context = new PropertiesHdApplicationContext("application.properties");
        //com.hongdu.spring.context.PropertiesHdApplicationContext@20398b7c
        try {
            Object object = context.getBean(MyAction.class);

            System.out.println(object.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
