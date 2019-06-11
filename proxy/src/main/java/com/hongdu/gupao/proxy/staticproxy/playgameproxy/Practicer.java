package com.hongdu.gupao.proxy.staticproxy.playgameproxy;

import com.hongdu.gupao.proxy.IGamePlayer;

/**
 * 代练
 */
public class Practicer implements IGamePlayer {

    private IGamePlayer player;//代练持有玩家引用 可以代理玩家的每个方法，前提是自己这里要重写一次

    public Practicer(IGamePlayer player) {
        this.player = player;
    }

    @Override
    public void login(String user, String password) {
        this.player.login(user,password);
    }

    @Override
    public void killBoss() {
        this.player.killBoss();
    }

    @Override
    public void upgrader() {
        this.player.upgrader();
    }

    @Override
    public void killPerson() {
        this.player.killPerson();
    }
}
