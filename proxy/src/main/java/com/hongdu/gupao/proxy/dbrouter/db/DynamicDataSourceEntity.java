package com.hongdu.gupao.proxy.dbrouter.db;

/**
 * ThreadLocal<></>
 */
public class DynamicDataSourceEntity {

    private static final String DEFAULT_SOURCE = null;
    //在线程内部是可以设置值的
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static void clear() {
        local.set(DEFAULT_SOURCE);
    }

    //set
    public static void set(String source) {
        local.set(source);
    }

    //get
    public static String get() {
        return local.get();
    }



    //私有化构造器
    private DynamicDataSourceEntity(){}

    //根据年份切换到对应的数据源 DB_ + year
    public static void set(int year) {
        local.set("DB_" + year);
    }

}
