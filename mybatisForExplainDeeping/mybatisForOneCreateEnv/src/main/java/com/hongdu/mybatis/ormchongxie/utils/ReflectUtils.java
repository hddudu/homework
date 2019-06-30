package com.hongdu.mybatis.ormchongxie.utils;

import com.hongdu.mybatis.ormchongxie.bean.ColumnInfo;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @ClassName ReflectUtils
 * @Description 用于反射相关的操作
 * @Author dudu
 * @Date 2019/6/30 17:09
 * @Version 1.0
 */
public class ReflectUtils {


    /**
     * 通过反射调用某个实体类的属性的get方法
     * @param object
     * @param fieldName
     * @return
     */
    public static Object invokeGet(Object object, String fieldName) {
        try {
            Class<?> clazz = object.getClass();
            Method method = clazz.getMethod("get" + StringUtils.upFirstString(fieldName), null);
            /**
             * get方法是无参的！
             */
            return method.invoke(object, null);
        } catch (Exception e) {
            System.out.println("反射调用方法get错误！" + "get" + StringUtils.upFirstString(fieldName));
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void invokeGetTest() {
        ColumnInfo columnInfo = new ColumnInfo();
        System.out.println("反射调用之前： " + columnInfo);
        String name = "name";
        String value = "张阿森纳";
        columnInfo.setName(value);
        System.out.println("反射调用之后： " + invokeGet(columnInfo, name));
    }

    /**
     * f反射调用set方法有一个参数：
     *  参数名 + 参数值
     * 通过反射调用属性的set方法设值
     * @param object
     * @param columnLabel
     * @param columnValue
     * @return
     */
    public static Object invokeSet(Object object, String columnLabel, Object columnValue) {
        try {
            if(columnValue != null) {
                Class<?> clazz = object.getClass();
                Method method = clazz.getMethod("set" + StringUtils.upFirstString(columnLabel), columnValue.getClass());
                return method.invoke(object, columnValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用set方法失败！");
        }
        return null;
    }

    @Test
    public void invokeSetTest() {
        ColumnInfo columnInfo = new ColumnInfo();
        String name = "name";
        String value = "张阿森纳";
        System.out.println("反射调用之前： " + columnInfo);
        invokeSet(columnInfo, name, value);
        System.out.println("反射调用之后： " + columnInfo);
    }

}
