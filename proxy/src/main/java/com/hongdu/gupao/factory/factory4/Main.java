package com.hongdu.gupao.factory.factory4;

import com.hongdu.gupao.factory.factory4.factory.Provider;
import com.hongdu.gupao.factory.factory4.factory.Sender;
import com.hongdu.gupao.factory.factory4.factory.impl.PhoneSenderFactory;
import com.hongdu.gupao.factory.factory4.factory.impl.SendMailFactory;

/**
 * @ClassName Main
 * @Description 工厂模式适合：凡是出现了大量的产品需要创建，并且具有共同的接口时，可以通过工厂方法模式进行创建。
 * 在以上的三种模式中，第一种如果传入的字符串有误，不能正确创建对象，
 * 第三种相对于第二种，不需要实例化工厂类，
 * 所以，大多数情况下，我们会选用第三种——静态工厂方法模式。
 * @Author dudu
 * @Date 2019/7/19 11:44
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        Provider provider = new SendMailFactory();
        Sender sender = provider.produce();
        sender.send();
        //那么我现在要扩展一个： 短信发送， 邮件发送， 电话推销的方式
        provider = new PhoneSenderFactory();
        provider.produce().send();
        //意思是 ： 扩展不用改代码， 只是需要添添加类 ： 也是一个弊病
    }
}
