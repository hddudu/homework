package com.hongdu.mybatis.ormchongxie.utils;

import org.junit.Test;

/**
 * @ClassName StringUtils
 * @Description 用来字符串相关操作
 * @Author dudu
 * @Date 2019/6/30 17:11
 * @Version 1.0
 */
public class StringUtils {

    /**
     * 将字符串第一个字符大写，并去掉其中的下划线
     * @param s
     * @return
     */
    public static String upFirstString(String s) {
        s = s.replace("_", "");
        s = s.substring(0,1).toUpperCase() + s.substring(1);
        return s;
    }
    @Test
    public void upFirstStringTest() {
        String s = "abcddaer__11_11_11";
        System.out.println(upFirstString(s));
    }


    /**
     * 去掉字符串中的下划线
     * @param s
     * @return
     */
    public static String trimUnderLine(String s) {
        s = s.replace("_", "");
        return s;
    }

}
