package com.hongdu.interviewforbigfactory.reflectinterview;

import java.lang.reflect.Field;

/**
 * 反射修改对象值
 */
public class TestReflectA2B {
    public static void main(String[] args) throws Exception{
        ReflectPoint reflectPoint = new ReflectPoint(3,4);
        System.out.println(reflectPoint);
        changeB2A(reflectPoint);
        System.out.println(reflectPoint);

    }

    private static void changeB2A(Object obj) throws Exception {
        Field[] fields = obj.getClass().getFields();

        for (Field f : fields) {
            //if(field.getType().equals(String.class))
            //由于字节码只有一份,用equals语义不准确
            System.out.println("f.getType: "+f.getType());
            if (f.getType() == String.class) {
                String oldValue = (String)f.get(obj);
                String newValue = oldValue.replace('b','a');//已经将b替换了
                f.set(obj,newValue);
            }
        }
    }


}

class ReflectPoint{
    private int x = 0;
    public int y = 0;
    public String str1 = "ball";
     public String str2 = "basketball";
     public String str3 = "itcat";

    public ReflectPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
     public String toString() {
         return "ReflectPointer [str1=" + str1 + ", str2=" + str2 + ", str3="
                 + str3 + "]";
    }
}
