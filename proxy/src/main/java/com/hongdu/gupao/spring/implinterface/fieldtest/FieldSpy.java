package com.hongdu.gupao.spring.implinterface.fieldtest;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @ClassName FieldSpy
 * @Description 字段 Field 对象测试
 * @Author dudu
 * @Date 2019/7/8 16:18
 * @Version 1.0
 */
public class FieldSpy<T> {
    public boolean[][] b = {{ false, false }, { true, true } };
    public String name  = "Alice";
    public List<Integer> list;
    public T val;

    public static void main(String[]  args) {
//        args = new String[]{FieldSpy.class.getName(),"b"};
        String[] argss = new String[]{FieldSpy.class.getName(),"val"};
        try {
            Class<?> c = Class.forName(argss[0]);
            Field f = c.getField(argss[1]);
            System.out.format("Type: %s%n", f.getType()); //getType()返回字段的类型信息
            //Type: class java.lang.Object : type： 对应的是类 + 类的类型
            //Type: java.lang.Object        getName  ： 返回的只有类的类型
            //GenericType: T
            System.out.format("Type: %s%n", f.getType().getName()); //getType()返回字段的类型信息
            System.out.format("GenericType: %s%n", f.getGenericType());//返回字段的泛型类型信息
            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        } catch (NoSuchFieldException x) {
            x.printStackTrace();
        }
    }
}
