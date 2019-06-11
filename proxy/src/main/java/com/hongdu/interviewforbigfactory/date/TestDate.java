package com.hongdu.interviewforbigfactory.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
    public static void main(String[] args) throws ParseException {
        System.out.println(formatDate2Ymh(new Date()));
        String strDate="2005年4月22日";
        //注意：SimpleDateFormat构造函数的样式与strDate的样式必须相符
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 ");
//        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
        //必须捕获异常
        try {
            Date date=simpleDateFormat.parse(strDate);
            System.out.println(date);
        } catch(ParseException px) {
            px.printStackTrace();
        }
    }

    private static Date formatDate2Ymh(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sdfDate = sdf.format(date);
        System.out.println(sdfDate);
        //将日期格式化为 yyyy-MM-dd hh:mm:ss 形式
        Date retFormatDate = sdf.parse("2019-06-11 11:12:17");
        return retFormatDate;
    }
}
