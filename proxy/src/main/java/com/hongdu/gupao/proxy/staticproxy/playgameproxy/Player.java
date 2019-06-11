package com.hongdu.gupao.proxy.staticproxy.playgameproxy;

import com.hongdu.gupao.proxy.IGamePlayer;

/**
 * 玩家玩游戏
 */
public class Player implements IGamePlayer {

    //玩家属性：
    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public void login(String user, String password) {
        System.out.println("输入账号密码登陆游戏");
        System.out.println("登陆名为" + user + "的用户" + this.name + "登陆成功!欢迎！");
    }

    @Override
    public void killBoss() {
        System.out.println(this.name + "杀怪，杀领主！");
    }

    @Override
    public void upgrader() {
        System.out.println(this.name + "又升了一级！");
    }

    @Override
    public void killPerson() {
        System.out.println("人机模式杀死英雄关羽，阿珂，露娜，三杀，nice！");
    }
}
