package com.hongdu.gupao.wrapper;

import com.hongdu.gupao.wrapper.v1.BaseWrapperWithAttr;

/**
 * @ClassName BaseSpringProcessor
 * @Description 包装处理器
 * @Author dudu
 * @Date 2019/7/15 13:30
 * @Version 1.0
 */
public class BaseSpringProcessor {

    /***
     * 前置处理
     * @param bean
     * @return
     * @throws Exception
     */
    public Object postProcessBeforeInitialization(Object bean ) throws Exception {
        return bean;
    }

    /**
     * 后置处理 : 将对象进行后置包装
     * @param bean
     * @param
     * @return
     * @throws Exception
     */
    public Object postProcessAfterInitialization(Object bean) throws Exception {
        //添加属性
        BaseSpring baseSpring = (BaseSpring) bean;
        BaseWrapperWithAttr attr = new BaseWrapperWithAttr(baseSpring);
        attr.setNio("nio");
        attr.setSoa("soa");
        return attr;
    }
}
