package com.hongdu.gupao.factory.factory4.factory.impl;

import com.hongdu.gupao.factory.factory4.SmsSender;
import com.hongdu.gupao.factory.factory4.factory.Provider;
import com.hongdu.gupao.factory.factory4.factory.Sender;

/**
 * @ClassName SendMailFactory
 * @Description 就是创建要给抽象的工厂
 * @Author dudu
 * @Date 2019/7/19 11:50
 * @Version 1.0
 */
public class SmsSenderFactory implements Provider {

    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
