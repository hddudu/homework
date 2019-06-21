package com.hongdu.gupao.template.jdbctemplate;

import java.sql.ResultSet;

/**
 * @ClassName RowMapper
 * @Description ORM框架的基础接口 ： 行 映射
 * @Author dudu
 * @Date 2019/6/19 19:40
 * @Version 1.0
 */
public interface RowMapper<T> {

    /**
     * map 行
     * @param rs
     * @param rowNum
     * @return
     * @throws Exception
     */
    T mapRow(ResultSet rs, int rowNum) throws Exception;
}
