package com.hongdu.gupao.mebatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName HdMapperProxy
 * @Description mapper的代理对象生成类: 实现了jdk的代理对象的必须实现的接口
 * @Author dudu
 * @Date 2019/8/4 15:49
 * @Version 1.0
 */
public class HdMapperProxy implements InvocationHandler {

    //被代理的目标类 ： 直接传入 sqlSssin
    //然后直接new对象后，该代理对象就持有了sqlsession的引用，就拥有了操作数据库的能力
    //private Object target;
    private HdSqlSession sqlSession;

    public HdMapperProxy(HdSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //全路径名
        String statementId = method.getDeclaringClass().getName() + "." + method.getName();
        return this.sqlSession.selectOne(statementId, args[0]);
    }
}
