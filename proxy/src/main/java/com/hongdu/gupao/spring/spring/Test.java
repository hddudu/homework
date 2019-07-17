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
            //查看初始化后： factoryBeanInstanceCaches 的属性 ： 里面需要有 一个类依赖另一个类的属性值
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
