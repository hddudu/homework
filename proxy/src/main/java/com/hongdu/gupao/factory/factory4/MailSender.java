package com.hongdu.gupao.factory.factory4;


import com.hongdu.gupao.factory.factory4.factory.Sender;

/**
 * @ClassName MailSender
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/19 11:41
 * @Version 1.0
 */
public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is mailsender!");
    }
}
