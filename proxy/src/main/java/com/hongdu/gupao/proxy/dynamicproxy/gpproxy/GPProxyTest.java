package com.hongdu.gupao.proxy.dynamicproxy.gpproxy;

import com.hongdu.gupao.proxy.Person;
import com.hongdu.gupao.proxy.staticproxy.girlsproxy.Girl;

public class GPProxyTest {

    public static void main(String[] args) {
        try {
            Person obj = (Person) new GPMeiPo().getInstance(new Girl("马伊利",20));
            obj.findLover();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
