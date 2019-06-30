package com.hongdu.mybatis.one.test;

import com.hongdu.mybatis.one.entity.Role;
import com.hongdu.mybatis.one.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @ClassName TestOne
 * @Description 测试
 * @Author dudu
 * @Date 2019/6/29 23:20
 * @Version 1.0
 */
public class TestOne {

    /**
     * 数据库连接测试
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void test() throws SQLException, ClassNotFoundException {
//        String drivers = prop.getProperty("jdbc,drivers");
//        if (drivers != null) {
//            System.setProperty("jdbc.drivers", drivers);
//        }
//        String url = prop.getProperty("jdbc.url");
//        String username = prop.getProperty("jdbc.username");
//        String password = prop.getProperty("jdbc.password");
//        Class.forName("com.mysql.jdbc.Driver");
        DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis-one-getin", "root", "123456");
        System.out.println("success!");
    }

    @Test
    public void getUserTest() {
        //定义读取文件名
        String resources = "mybatis-config.xml";
         //创建流
         Reader reader=null;
        try {
                 //读取mybatis-config.xml文件到reader对象中
                 reader= Resources.getResourceAsReader(resources);
             } catch (IOException e) {
                 e.printStackTrace();
             }
         //初始化mybatis,创建SqlSessionFactory类的实例
         SqlSessionFactory sqlMapper=new SqlSessionFactoryBuilder().build(reader);
         //创建session实例
         SqlSession session=sqlMapper.openSession();
         //传入参数查询，返回结果
         User user=session.selectOne("getUser",1);
         //输出结果
         System.out.println(user.getName());
         //关闭session
        session.close();
    }

    @Test
    public void getRoleTest() {
        //定义读取文件名
        String resources = "mybatis-config.xml";
        //创建流
        Reader reader=null;
        try {
            //读取mybatis-config.xml文件到reader对象中
            reader= Resources.getResourceAsReader(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //初始化mybatis,创建SqlSessionFactory类的实例
        SqlSessionFactory sqlMapper=new SqlSessionFactoryBuilder().build(reader);
        //创建session实例
        SqlSession session=sqlMapper.openSession();
        //传入参数查询，返回结果
        Role role=session.selectOne("getRole",1);
        //输出结果
        System.out.println(role.getRoleName());
        //关闭session
        session.close();
    }
}
