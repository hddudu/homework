package com.hongdu.gupao.spring.v3.strategies;

/**
 * @ClassName Convertor
 * @Description 类型转换器
 * @Author dudu
 * @Date 2019/7/11 10:10
 * @Version 1.0
 */
public interface Convertor {

    Object convert(Class<?> type, String value);
}
