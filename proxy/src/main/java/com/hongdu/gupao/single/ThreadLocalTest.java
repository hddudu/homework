package com.hongdu.gupao.single;

/**
 * @ClassName ThreadLocalTest
 * @Description 在一个线程 内部 这个变量是是安全的 ： Session是安全的  是隔离的
 *      就是多个用户登录后， 都需要记录sesssion， 每个用户登录后， 都会有一个线程来记录用户信息
 *      一个用户点击登录就会跑一段代码， 这段代码就是线程
 *          因为服务端后台一般是部署一个， 前台访问页面使用的人可能有多个，
 *          假如2个人同时登录， 都会调用登录代码逻辑， 同时会记录session，
 *              2个人同时跑相同的代码几率为0， 所以没有并发问题， 但是我们仍然要考虑并发性
 *              如果是10000个人， 那么几率是100%了；
 *              如果存在共享变量， 必然会有线程安全性 ：
 *                  因为多个线程会使用相同的变量， 会操作错乱
 * @Author dudu
 * @Date 2020/2/26 22:41
 * @Version 1.0
 */
public class ThreadLocalTest {
    private static final ThreadLocal threadSession = new ThreadLocal();

    public static Session getSession() throws InfrastructureException {
        Session s = (Session) threadSession.get();
        if (s == null) {
            s = new Session();
            threadSession.set(s);
        }
        return s;
    }
}
