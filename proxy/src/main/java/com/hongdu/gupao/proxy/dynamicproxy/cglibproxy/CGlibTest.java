package com.hongdu.gupao.proxy.dynamicproxy.cglibproxy;

public class CGlibTest {


    public static void main(String[] args) {
        try {
            Customer obj = (Customer) new CGlibMeiPo().getInstance(Customer.class);//alt+enter 或者 ctrl + alt + T
            obj.findLover();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
