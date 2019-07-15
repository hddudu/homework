package com.hongdu.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @ClassName MainEnter
 * @Description 开启dubbo服务 注册服务
 * @Author dudu
 * @Date 2019/7/14 17:13
 * @Version 1.0
 */
public class MainEnter {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationProvider.xml");
        context.start();

        System.out.println("-----dubbo开启-----");

        System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟

    }
}
