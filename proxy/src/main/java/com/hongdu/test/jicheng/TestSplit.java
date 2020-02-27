package com.hongdu.test.jicheng;

/**
 * @ClassName TestSplit
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/26 18:14
 * @Version 1.0
 */
public class TestSplit {
    public static void main(String[] args) {
        String ss = "amount";
        String[] sss = ss.split(",");
        System.out.println(sss.length);
    }
}
