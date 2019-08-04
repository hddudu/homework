package com.hongdu.gupao.mebatis;

import java.sql.*;


/**
 * @ClassName HdExecutor
 * @Description sql执行器 ： mybatis中有装饰模式 ： 简单 + 可重用 + 可批量执行
 * @Author dudu
 * @Date 2019/8/4 15:42
 * @Version 1.0
 */
public class HdExecutor {

    /**
     *
     * @param sql
     * @param
     * @param <T>
     * @return
     */
//    public <T> T query(String sql, Object parameter) {
//        Connection conn = null;
//        Statement stmt = null;
//        Blog blog = new Blog();
//
//        try {
//            // 注册 JDBC 驱动
//            Class.forName("com.mysql.jdbc.Driver");
//
//            // 打开连接
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gp-mybatis", "root", "123456");
//
//            // 执行查询
//            stmt = conn.createStatement();
//            //替换占位符
//            //System.out.println("执行的sql语句： " + String.format(sql, parameter));
//            ResultSet rs = stmt.executeQuery(String.format(sql, parameter));
//
//            // 获取结果集
//            while (rs.next()) {
//                Integer bid = rs.getInt("bid");
//                String name = rs.getString("name");
//                Integer authorId = rs.getInt("author_id");
//                blog.setAuthorId(authorId);
//                blog.setBid(bid);
//                blog.setName(name);
//            }
//            System.out.println(blog);
//
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (SQLException se) {
//            se.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (stmt != null) stmt.close();
//            } catch (SQLException se2) {
//            }
//            try {
//                if (conn != null) conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        return (T) blog;
//    }
    public <T> T query(String sql, Object paramater) {
        Connection conn = null;
        Statement stmt = null;
        Blog blog = new Blog();

        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gp-mybatis", "root", "123456");

            // 执行查询
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(String.format(sql, paramater));

            // 获取结果集
            while (rs.next()) {
                Integer bid = rs.getInt("bid");
                String name = rs.getString("name");
                Integer authorId = rs.getInt("author_id");
                blog.setAuthorId(authorId);
                blog.setBid(bid);
                blog.setName(name);
            }
            System.out.println(blog);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return (T)blog;
    }
}
