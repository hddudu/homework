package com.hongdu.mybatis.ormchongxie.core.service.impl;

import com.hongdu.mybatis.ormchongxie.bean.ColumnInfo;
import com.hongdu.mybatis.ormchongxie.bean.DBConfiguration;
import com.hongdu.mybatis.ormchongxie.bean.TableInfo;
import com.hongdu.mybatis.ormchongxie.core.DBManager;
import com.hongdu.mybatis.ormchongxie.core.TableContext;
import com.hongdu.mybatis.ormchongxie.core.service.IQuery;
import com.hongdu.mybatis.ormchongxie.utils.JdbcUtils;
import com.hongdu.mybatis.ormchongxie.utils.ReflectUtils;
import javafx.scene.control.Tab;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName IQueryImpl
 * @Description
 * @Author dudu
 * @Date 2019/6/30 17:02
 * @Version 1.0
 */
public abstract class IQueryImpl implements IQuery {

    /**
     * 执行DML语句
     * @param sql
     * @param params
     * @return
     */
    @Override
    public int exeuteDML(String sql, Object[] params) {
        /**
         * 从连接池中获取连接
         */
        Connection connection = DBManager.getConnection();
        int count = 0;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            JdbcUtils.handlerParams(ps, params);
            System.out.println("sql : "+sql);
            System.out.println("PreparedStatement : "+ps);
            count = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("数据库操作失败！");
        } finally {
            DBManager.close(connection, ps);
        }
        System.out.println("count: " + count);
        return count;
    }

    /**
     * 将一个对象存储到数据库
     * @param object
     * @return
     */
    @Override
    public int save(Object object) {
        // insert into logs(a,b) values (?,?)
        Class<?> clazz = object.getClass();
        /**
         * 变量默认初始化为0
         */
        int count;
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        StringBuilder sb = new StringBuilder("insert into " + tableInfo.getName() + "( ");
        //得到属性
        Field[] fields = clazz.getDeclaredFields();

        List<Object> fieldValueList = new ArrayList<>();
        for (Field field : fields) {
            String name = field.getName();
            /**
             * 调用对象 + 名字（参数名字）
             */
            Object value = ReflectUtils.invokeGet(object, name);
            if (value != null) {
                sb.append(name + ",");
                fieldValueList.add(value);
            }
        }
        /**
         * 将最后一个 ， 换成 ）
         */
        sb.setCharAt(sb.length() -1, ')');
        sb.append(" values(");

        for (int i = 0; i < fieldValueList.size(); i++) {
            /**
             * 这个是参数的格式
             */
            sb.append("?,");
        }
        sb.setCharAt(sb.length() - 1, ')');
        count = exeuteDML(sb.toString(),fieldValueList.toArray());
        return count;
    }

    /**
     * 删除一个对象
     * @param object
     * @return
     */
    @Override
    public int delete(Object object) {
        Class<?> clazz = object.getClass();
        /**
         * 获取 表信息 实体bean
         */
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey();
        String sql = "delete from" + tableInfo.getName() + " where " + onlyPriKey.getName() + "=?";

        //反射调用get方法 ： 得到s属性的值
        Object o = ReflectUtils.invokeGet(object, onlyPriKey.getName());
        return exeuteDML(sql, new Object[]{o});
    }

    /**
     * 删除类 对应的表中的s数据 删除该id的对象
     * @param clazz
     * @param id
     * @return
     */
    @Override
    public int delete(Class clazz, Object id) {
        // delete from logs where id=?
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey();
        String sql = "delete from" + tableInfo.getName() + " where " + onlyPriKey.getName() + "=?";
        return exeuteDML(sql.toString(), new Object[]{id});
    }

    /**
     * 更新对象字段的信息
     * @param object
     * @param fieldNames
     */
    @Override
    public void update(Object object, String[] fieldNames) {

        //obj{"uanme","pwd"}-->update 表名  set uname=?,pwd=? where id=?
        Class clazz = object.getClass();
        /**
         * 存储ssql的参数对象
         */
        List<Object> params = new ArrayList<>();
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        //获得唯一的主键
        ColumnInfo priKey = tableInfo.getOnlyPriKey();
        StringBuilder sql = new StringBuilder("update " + tableInfo.getName() + " set ");

        for (String fname : fieldNames) {
            Object fvalue = ReflectUtils.invokeGet(object, fname);
            params.add(fvalue);
            sql.append(fname+"=?,");
        }
        sql.setCharAt(sql.length() - 1, ' ');
        sql.append(" where ");
        sql.append(priKey.getName() + "=? ");

        /**
         * 主键的值
         */
        params.add(ReflectUtils.invokeGet(object, priKey.getName()));
        exeuteDML(sql.toString(), params.toArray());
    }

    /**
     *  根据参数，查询指定的数据，多行记录，单行记录可直接get(0)
     * @param sql
     * @param clazz
     * @param params
     * @return
     */
    @Override
    public List queryRows(String sql, Class clazz, Object[] params) {
        Connection connection = DBManager.getConnection();
        PreparedStatement ps = null;
        List<Object> rows = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            ps = connection.prepareStatement(sql);
            JdbcUtils.handlerParams(ps, params);
            resultSet = ps.executeQuery();
            //得到返回结果有多少列
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                Object o = clazz.newInstance();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    //得到每y一列的名称
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);
                    /**
                     * set ： 方法
                     */
                    ReflectUtils.invokeSet(o, columnLabel, columnValue);
                }
                rows.add(o);
            }
            return rows;
        } catch (Exception e) {
            System.out.println("准备sql完毕成功！");
        } finally {
            DBManager.close(connection, ps);
        }
        return null;
    }

    /**
     * 查询某个字段的数据
     * @param sql
     * @param params
     * @return 封装查询d到的数据
     */
    @Override
    public Object queryValue(String sql,Object[] params){
        Connection connection = DBManager.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Object o = null;
        try {
            ps = connection.prepareStatement(sql);
            JdbcUtils.handlerParams(ps, params);
            rs = ps.executeQuery();
            while (rs.next()) {
                o = rs.getObject(1);
            }
        } catch (SQLException e) {
            System.out.println("准备sql失败！");
            return null;
        } finally {
            DBManager.close(connection, ps);
        }
        return o;
    }

    @Test
    public void arrayTest() {
        String[] strings = new String[]{"aaa","bbbbb","cccc","ddddd"};
        System.out.println(Arrays.asList(strings));
        System.out.println(Arrays.toString(strings));
    }

}
