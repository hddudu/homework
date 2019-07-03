package com.hongdu.interviewforbigfactory;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JunitTest {

    @Test
    public void test() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        System.out.println(cal.getTime());
        //日期格式化成 yyyyMMddHHmmss格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String sss = simpleDateFormat.format(cal.getTime());
        System.out.println(sss);
    }
}
