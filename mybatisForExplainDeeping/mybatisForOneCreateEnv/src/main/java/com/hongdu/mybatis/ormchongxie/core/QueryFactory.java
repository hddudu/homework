package com.hongdu.mybatis.ormchongxie.core;

import com.hongdu.mybatis.ormchongxie.core.service.IQuery;

/**
 * @ClassName QueryFactory
 * @Description 负责根据配置文件，管理query对象
 * @Author dudu
 * @Date 2019/6/30 21:00
 * @Version 1.0
 */
public class QueryFactory {

    private static QueryFactory queryFactory = new QueryFactory();

    private static Class c;

    /**
     * 私有化构造器
     */
    private QueryFactory() {}

    /**
     * 静态块初始化
     */
    static {
        if(DBManager.getDbConfiguration().getUseDB().equalsIgnoreCase("mysql")) {
            try {
                /**
                 *  类对象 ： MysqlQuery
                 */
                c = Class.forName("com.hongdu.mybatis.ormchongxie.core.service.MysqlQuery");
            } catch (ClassNotFoundException e) {
                System.out.println("mysql的查询器！");
            }
        }
        //将对应的类和表封装起来
        TableContext.loadTablePoToMap();
    }

    /**
     * 静态化 加载类对象后，实例化对象
     */
    public static IQuery createQuery() {
        try {
            return  (IQuery) c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}
