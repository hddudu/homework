package com.hongdu.gupao.prototype;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName PrototypTest
 * @Description TODO
 * @Author dudu
 * @Date 2020/3/1 16:21
 * @Version 1.0
 */
public class PrototypTest {
    public PrototypTest jsonClone() {
        PrototypTest prototypTest = this;
        return  JSON.toJavaObject((JSON)JSONObject.toJSON(prototypTest), PrototypTest.class);
    }
}
