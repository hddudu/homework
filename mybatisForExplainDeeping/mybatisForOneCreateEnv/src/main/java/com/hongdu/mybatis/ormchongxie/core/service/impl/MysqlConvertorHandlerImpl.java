package com.hongdu.mybatis.ormchongxie.core.service.impl;

import com.hongdu.mybatis.ormchongxie.core.service.TypeConvertorHandler;

/**
 * @ClassName MysqlConvertorHandlerImpl
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/30 18:53
 * @Version 1.0
 */
public class MysqlConvertorHandlerImpl implements TypeConvertorHandler {


    @Override
    public String javaType2JdbcType(String javaType) {
        return null;
    }

    @Override
    public String jdbcType2JavaType(String jdbcTypeData) {
        //varchar-->String
        if("varchar".equalsIgnoreCase(jdbcTypeData)||"char".equalsIgnoreCase(jdbcTypeData)){
            return "String";
        }else if("int".equalsIgnoreCase(jdbcTypeData)
                ||"tinyint".equalsIgnoreCase(jdbcTypeData)
                ||"smallint".equalsIgnoreCase(jdbcTypeData)
                ||"integer".equalsIgnoreCase(jdbcTypeData)
        ){
            return "Integer";
        }else if("bigint".equalsIgnoreCase(jdbcTypeData)){
            return "Long";
        }else if("double".equalsIgnoreCase(jdbcTypeData)||"float".equalsIgnoreCase(jdbcTypeData)){
            return "Double";
        }else if("clob".equalsIgnoreCase(jdbcTypeData)){
            return "CLob";
        }else if("blob".equalsIgnoreCase(jdbcTypeData)){
            return "BLob";
        }else if("date".equalsIgnoreCase(jdbcTypeData)){
            return "Date";
        }else if("time".equalsIgnoreCase(jdbcTypeData)){
            return "Time";
        }else if("timestamp".equalsIgnoreCase(jdbcTypeData)){
            return "Timestamp";
        }
        return null;
    }
}
