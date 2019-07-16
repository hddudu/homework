package com.hongdu.one.date.cal;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateDiff
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/1 17:15
 * @Version 1.0
 */
public class DateDiff {

    @Test
    public void test01() {
        Date cur = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(cur);
        System.out.println(sdf.format(cur));
        System.out.println(Long.parseLong(sdf.format(cur)));
        Long i = 0L;
        System.out.println(i == 0);
    }

    @Test
    public void test02() {
//        2020-07-05 16:41:33.000
        Date cur = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(cur);
        System.out.println(sdf.format(cur));
        //20190715172239
        System.out.println(Long.valueOf(sdf.format(cur)));

    }
}
