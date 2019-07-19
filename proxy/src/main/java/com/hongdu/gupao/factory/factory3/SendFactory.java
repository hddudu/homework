package com.hongdu.gupao.factory.factory3;

/**
 * @ClassName SendFactory
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/19 11:42
 * @Version 1.0
 */
public class SendFactory {
//    public Sender produce(String type) {
//        if("mail".equalsIgnoreCase(type)) {
//            return new MailSender();
//        } else if("sms".equalsIgnoreCase(type)) {
//            return new SmsSender();
//        } else {
//            System.out.println("请输入正确的类型!");
//            return null;
//        }
//    }

    public static Sender produceMail() {
        return new MailSender();
    }

    public static Sender produceSms() {
        return new SmsSender();
    }
}
