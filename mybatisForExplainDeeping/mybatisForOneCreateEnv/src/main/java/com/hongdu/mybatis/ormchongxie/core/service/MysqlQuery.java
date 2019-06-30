package com.hongdu.mybatis.ormchongxie.core.service;

import com.hongdu.mybatis.ormchongxie.core.service.impl.IQueryImpl;

/**
 * @ClassName MySqlQuery
 * @Description
 * @Author dudu
 * @Date 2019/6/30 17:52
 * @Version 1.0
 */
public class MysqlQuery extends IQueryImpl {

    public static void main(String[] args){
//        Bean bean = new Bean();
//        bean.setTid(7);
//        bean.setName("dfasdj");
//        new MySqlQuery().insert(bean);

        String sql = "SELECT age FROM bean WHERE tid=?";
        //Object o = new MySqlQuery().queryValue(sql,new Object[]{4});
//        Bean bean = new Bean();
//        bean.setName("lisisis");
//        bean.setTid(8);
        //Object o = QueryFactory.createQuery().queryValue(sql, new Object[]{4});
//        new MySqlQuery().insert(bean);
//        System.out.println("ok");
//        long l1 = System.currentTimeMillis();
//        for (int i=0;i<1000;i++) {
//            String sql = "SELECT age FROM bean WHERE tid=?";
//            //Object o = new MySqlQuery().queryValue(sql,new Object[]{4});
//            Object o = QueryFactory.createQuery().queryValue(sql, new Object[]{4});
//            //System.out.println(o);
//        }
//        long l = System.currentTimeMillis();
//        System.out.println(l-l1);
        // JavaFileUtils.createJavaFileToPackage();
    }

}
