package com.hongdu.mybatis.ormchongxie.core.service;

/**
 * @ClassName TypeConvertorHandler
 * @Description 数据类型转换器
 * @Author dudu
 * @Date 2019/6/30 17:37
 * @Version 1.0
 */
public interface TypeConvertorHandler {

    /**
     * java数据类型转换成数据库数据类型
     * @param javaType
     * @return
     */
    String javaType2JdbcType(String javaType);

    /**
     * 数据库数据类型转换成java数据类型
     * @param jdbcTypeData
     * @return
     */
    String jdbcType2JavaType(String jdbcTypeData);

}
