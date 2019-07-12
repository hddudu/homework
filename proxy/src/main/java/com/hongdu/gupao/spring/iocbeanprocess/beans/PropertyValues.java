package com.hongdu.gupao.spring.iocbeanprocess.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PropertyValues
 * @Description T* 包装一个对象所有的
 *      PropertyValue。<br/>
 *  * 为什么封装而不是直接用List?因为可以封装一些操作。
 *
 *   一个类的待注入的属性 ： 会有很多个
 * @Author dudu
 * @Date 2019/7/11 18:55
 * @Version 1.0
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

    public PropertyValues() {
    }

    public void addPropertyValue(PropertyValue pv) {
        //TODO:这里可以对于重复propertyName进行判断，直接用list没法做到
        //不是很明白
        this.propertyValueList.add(pv);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    @Override
    public String toString() {
        return "PropertyValues{" +
                "propertyValueList=" + propertyValueList +
                '}';
    }
}
