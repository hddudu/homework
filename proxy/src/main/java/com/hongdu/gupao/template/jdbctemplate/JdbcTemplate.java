package com.hongdu.gupao.template.jdbctemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName JdbcTemplate
 * @Description 抽象类 ： 数据库操作模板
 * @Author dudu
 * @Date 2019/6/19 19:45
 * @Version 1.0
 */
public abstract class JdbcTemplate {
//    org.springframework.jdbc.core.JdbcTemplate
    //如何传入数据源
    /**
     * 1、class.forName()加载数据驱动
     *
     * 2、DriverManager.getConnection()获取数据库连接对象。
     *
     * 3、根据SQL或sql会话对象，有两种方式Statement、PreparedStatement。
     *
     * 4、执行sql处理结果集，如果有参数就设置参数。
     *
     * 5、关闭结果集，关闭会话，关闭资源。
     */

    /**
     * 1: 加载驱动 ==> 暂时省略： 这里的驱动又会有多个驱动，可以使用策略模式（不同的数据源连接埠同的数据库）
     * 2： 创建连接
     * 3： 创建语句集
     * 4： 执行语句集合
     * 5： 处理结果集合
     * 6： 关闭结果集
     * 7： 关闭语句集
     * 8： 关闭连接
     * （可以将关闭统一放到一个close中）
     */
    private DataSource dataSource;

    /**
     * 初始化数据源
     * @param dataSource
     */
    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 执行查询
     * @param sql sql语句
     * @param rowMapper 行映射
     * @param values 查询参数
     * @return
     */
    public List<?> executeQuery(String sql, RowMapper<?> rowMapper, Object[] values) {
        try {
            Connection connection= this.getConnection();
            PreparedStatement preparedStatement = this.createPrepareStatement(connection,sql);
            ResultSet rs = this.executeQuery(preparedStatement, values);
            List<?> result = this.parseResultSet(rs, rowMapper);
            this.closeResultSet(rs);
            this.closePrepareStatement(preparedStatement);
            this.closeConnection(connection);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void closeConnection(Connection connection) throws SQLException {
        //数据库连接池，我们不需要关闭
        connection.close();
    }

    protected void closePrepareStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    protected void closeResultSet(ResultSet rs) throws SQLException {
        rs.close();
    }

    protected List<?> parseResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
        List<Object> results = new ArrayList<>();
        int rowNum = 1;
        while (rs.next()) {
            results.add(rowMapper.mapRow(rs, rowNum++));
        }
        return results;
    }

//    private List<?> parseResultSet(ResultSet rs) {
//
//    }

    protected ResultSet executeQuery(PreparedStatement preparedStatement, Object[] values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i, values[i]);
        }
        return preparedStatement.executeQuery();
    }

    protected PreparedStatement createPrepareStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }


}
