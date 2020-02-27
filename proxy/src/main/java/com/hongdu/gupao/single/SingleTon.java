package com.hongdu.gupao.single;

/**
 * @ClassName SingleTon
 * @Description 懒汉 不线程安全
 * @Author dudu
 * @Date 2020/2/26 20:55
 * @Version 1.0
 */
public class SingleTon {

    private static SingleTon singleTon = null;

    private SingleTon() {}

    public static SingleTon getInstance() {
        if(singleTon == null) {
            singleTon = new SingleTon();
        }
        return singleTon;
    }

}
