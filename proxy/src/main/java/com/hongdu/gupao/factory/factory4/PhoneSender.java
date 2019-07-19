package com.hongdu.gupao.factory.factory4;

import com.hongdu.gupao.factory.factory4.factory.Sender;

/**
 * @ClassName PhoneSender
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/19 11:57
 * @Version 1.0
 */
public class PhoneSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is PhoneSender!");
    }
}
