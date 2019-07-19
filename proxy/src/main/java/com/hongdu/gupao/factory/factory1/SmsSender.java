package com.hongdu.gupao.factory.factory1;

/**
 * @ClassName MailSender
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/19 11:41
 * @Version 1.0
 */
public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is SmsSender!");
    }
}
