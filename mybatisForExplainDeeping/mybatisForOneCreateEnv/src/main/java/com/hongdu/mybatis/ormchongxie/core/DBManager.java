package com.hongdu.mybatis.ormchongxie.core;

import com.hongdu.mybatis.ormchongxie.bean.DBConfiguration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName DBManager
 * @Description 数据库管理核心类
 *      根据配置信息，维持连接对象的管理
 * @Author dudu
 * @Date 2019/6/30 10:12
 * @Version 1.0
 */
public class DBManager {

    private DBManager() {
    }

    private static DBConfiguration dbConfiguration;

    private static DBPool pool;

    /**
     * 静态代码块： 初始化数据库连接池 及 数据库连接池配置类
     */
    static {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
            dbConfiguration = new DBConfiguration();
            //根据properties构建configuration
            dbConfiguration.setDriver(properties.getProperty("driver"));
            dbConfiguration.setPackageName(properties.getProperty("packageName"));
            dbConfiguration.setPassword(properties.getProperty("password"));
            dbConfiguration.setUrl(properties.getProperty("url"));
            dbConfiguration.setUseDB(properties.getProperty("useDB"));
            dbConfiguration.setUserName(properties.getProperty("userName"));
            dbConfiguration.setPoolMinSize(Integer.parseInt(properties.getProperty("poolMinSize")));
            dbConfiguration.setPoolMaxSize(Integer.parseInt(properties.getProperty("poolMaxSize")));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static DBConfiguration getDbConfiguration() {
        return dbConfiguration;
    }

    //获取数据库连接 ： 因为这个才是对外的

    /**
     * 初始化的时候 就创建了数据连接
     * @return
     */
    public static Connection getConnection() {
        if (null == pool) {
            pool = new DBPool();
        }
        return pool.getConnection();
    }

    /**
     * 打开数据库连接
     * @return
     */
    public static Connection createConnection() {
        try {
            Class.forName(dbConfiguration.getDriver());
            return DriverManager.getConnection(dbConfiguration.getUrl()
                    ,dbConfiguration.getUserName(),dbConfiguration.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("类没有找到");
        } catch (SQLException e) {
            System.out.println("sql异常");
        }
        return null;
    }

    public static void close(Connection connection, PreparedStatement preparedStatement) {
        if(preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
