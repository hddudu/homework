package com.hongdu.gupao.spring.v3.strategies;

/**
 * @ClassName IntegerConvertor
 * @Description
 * @Author dudu
 * @Date 2019/7/11 10:12
 * @Version 1.0
 */
public class IntegerConvertor implements Convertor {

    @Override
    public Object convert(Class<?> type, String value) {
        return Integer.valueOf(value);
    }
}
