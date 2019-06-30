package com.hongdu.mybatis.ormchongxie.core.service;

import java.util.List;

/**
 * @ClassName Query
 * @Description
 *  负责查询，对外提供服务的核心类
 * @Author dudu
 * @Date 2019/6/30 16:57
 * @Version 1.0
 */
public interface IQuery {

    int exeuteDML(String sql, Object[] params);
     int save(Object object);
     int delete(Object object);
     int delete(Class clazz, Object id) ;
     void update(Object object, String[] fieldNames);
     List queryRows(String sql, Class clazz, Object[] params);
     Object queryValue(String sql,Object[] params);
}
