package com.hongdu.gupao.proxy;

/**
 * 游戏代练者 : ①游戏玩家亲自玩 : 耗时间 对身体不好(腰酸背痛) 娱乐伤身 最后还感觉不到自己厉害(被虐,等级低)
 *              Ⅱ通过代理请到了代练, 别人认识到的是账号主厉害, (付一点费用) 耗时间少
 */
public interface IGamePlayer {

    /**
     * 登陆游戏
     * @param user
     * @param password
     */
    void login(String user, String password);

    /**
     * 杀怪 : 网络游戏的主要特色
     */
    void killBoss();

    /**
     * 升级
     */
    void upgrader();

    /**
     * 竞技游戏的主要特色 : 超神, 暴走, 杀人最多, 助攻最多, MVP, 伤害最高, 逃跑, 挂机, 骂人, doubleKill, treplekill, 四杀, 五杀, 五连绝世等等等等..
     * KDA等等
     */
    void killPerson();
}
