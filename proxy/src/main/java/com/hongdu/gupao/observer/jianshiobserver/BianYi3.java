package com.hongdu.gupao.observer.jianshiobserver;

/**
 * @ClassName BianYi3
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/28 9:04
 * @Version 1.0
 */
public class BianYi3 implements JsObserver {

    private String name = "王五";

    @Override
    public void update(String message, String bname) {
        System.out.println(this.name + " : " +  bname + "那里有情况: " + message);
    }
}
