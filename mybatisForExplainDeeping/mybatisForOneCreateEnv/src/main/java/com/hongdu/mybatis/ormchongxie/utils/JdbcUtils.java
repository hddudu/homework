package com.hongdu.mybatis.ormchongxie.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName JdbcUtils
 * @Description 用于处理jdbc相关的操作
 * @Author dudu
 * @Date 2019/6/30 17:04
 * @Version 1.0
 */
public class JdbcUtils {

    /**
     * 给sql语句设值值
     * @param ps
     * @param params
     */
    public static void handlerParams(PreparedStatement ps, Object[] params) {
        if(params != null) {
            for (int i = 0; i < params.length; i++) {
                try {
                    ps.setObject(1 + i, params[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
