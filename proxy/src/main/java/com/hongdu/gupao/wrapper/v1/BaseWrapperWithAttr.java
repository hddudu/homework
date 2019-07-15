package com.hongdu.gupao.wrapper.v1;

import com.hongdu.gupao.wrapper.BaseSpring;
import lombok.Data;

/**
 * @ClassName BaseWrapperWithAttr
 * @Description
 * @Author dudu
 * @Date 2019/7/15 13:26
 * @Version 1.0
 */
@Data
public class BaseWrapperWithAttr {


    /**
     * 面向服务
     */
    private String soa;

    /**
     * 使用netty
     */
    private String nio;

    /**
     *  被包装的原装类
     */
    private BaseSpring baseSpring;

    public BaseWrapperWithAttr(BaseSpring baseSpring) {
        this.baseSpring = baseSpring;
    }

    public BaseWrapperWithAttr() {
    }
}
