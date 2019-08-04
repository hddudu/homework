package com.hongdu.gupao.mebatis;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 * @ClassName HdConfiguration
 * @Description 配置类
 * @Author dudu
 * @Date 2019/8/4 15:42
 * @Version 1.0
 */
public class HdConfiguration {

    //最简单的解析properties文件的方式
    public final static ResourceBundle sqlMappings;
    static {
        sqlMappings = ResourceBundle.getBundle("mesql");
    }

    //配置类中拥有这个引用吗？
    //private HdSqlSession sqlSession;

    /**
     * f返回接口的代理类对象
     * @param clazz 本身就是接口了
     * @param <T>
     * @return
     */
    /**
     *
     * @param clazz
     * @param sqlSession 直接在参数中传递更好 不再全局变量中进行初始化了， 因为操作数据库的会话史临时的！
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<?> clazz, HdSqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader()
            //, clazz.getInterfaces()
            , new Class[] {clazz}
            , new HdMapperProxy(sqlSession));
    }
}
