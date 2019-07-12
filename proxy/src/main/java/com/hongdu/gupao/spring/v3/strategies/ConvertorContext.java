package com.hongdu.gupao.spring.v3.strategies;

/**
 * @ClassName ConvertorContext
 * @Description 转换器的上下文
 *          持有接口引用
 * @Author dudu
 * @Date 2019/7/11 10:13
 * @Version 1.0
 */
public class ConvertorContext {

    /**
     * 持有转换器引用 ： 相当于依赖 - - - - - - - -- > 依赖
     */
    private Convertor convertor;

    /**
     * 传入的是什么样的转换器， 那么就会做怎样的转换
     * 如果判断转换器呢？ 只能通过类型的 if else 判断
     * @param convertor
     */
    public ConvertorContext(Convertor convertor) {
        this.convertor = convertor;
    }

    /**
     * 转换方法
     * @param type
     * @param value
     * @return
     */
    public Object convert(Class<?> type, String value) {
        return convertor.convert(type, value);
    }
}
