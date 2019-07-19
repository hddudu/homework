package com.hongdu.gupao.factory.factory1;

/**
 * @ClassName Main
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/19 11:44
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        SendFactory factory = new SendFactory();
        Sender sender = factory.produce("sms");
        sender.send();
    }
}
