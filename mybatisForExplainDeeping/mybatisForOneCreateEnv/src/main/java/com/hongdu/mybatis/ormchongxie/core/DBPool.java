package com.hongdu.mybatis.ormchongxie.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DBPool
 * @Description 数据库连接池独立成了一个bean
 *      连接池的管理：
 *      其实就是一个容器 ： 对容器内的对象进行 删除和新增的操作
 * @Author dudu
 * @Date 2019/6/30 10:12
 * @Version 1.0
 */
public class DBPool {
    /**
     * 数据库连接池对象 ： 多个
     * 在一个开始进行初始化，然后用的时候直接取，
     * 节省很多时间和资源开销
     */
    private List<Connection> pool;
    /**
     * 最大连接数
     */
    private static final int POOL_MAX_SIZE = DBManager.getDbConfiguration().getPoolMaxSize();
    /**
     * 最小连接数
     */
    private static final int POOL_MIN_SIZE = DBManager.getDbConfiguration().getPoolMinSize();

    public DBPool() {
        initPool();
    }

    /**
     * 初始化连接池 使池中的连接数达到最小值
     */
    public void initPool() {
        if(null == pool) {
            pool = new ArrayList<>();
        }
        while (pool.size() < POOL_MIN_SIZE) {
            /**
             * 创建连接来进行初始化
             */
            pool.add(DBManager.createConnection());
            System.out.println("初始化池，池中连接数："+pool.size());
        }
    }

    //取出连接

    /**
     * 从连接池中取出一个连接
     * @return
     */
    public synchronized Connection getConnection() {
        int lastIndex = pool.size() - 1;
        Connection connection = pool.get(lastIndex);
        pool.remove(connection);
        return connection;
    }

    //放回连接池

    /**
     * 新增数据库连接 ： 要先判断是否能新增
     * @param connection
     */
    public synchronized void close(Connection connection) {
        if(pool.size() >= POOL_MAX_SIZE) {
            try {
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            pool.add(connection);
        }
    }
}
