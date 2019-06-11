package com.hongdu.gupao.proxy.staticproxy.playgameproxy;

import com.hongdu.gupao.proxy.IGamePlayer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerTest {
    /**
     * 一直都是采用 接口做引用
     * @param args
     */
    public static void main(String[] args) {
//        Player zs = new Player("张三");
//        Practicer practicer = new Practicer(zs);
//
//        practicer.killBoss();

        IGamePlayer player = new Player("张三");

        IGamePlayer proxy = new Practicer(player);

        System.out.println("开始时间： " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        proxy.login("张三","123456");
        proxy.killBoss();
        proxy.upgrader();
        System.out.println("结束时间： " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

    }
}
