package com.hongdu.gupao.proxy.staticproxy.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hongdu.gupao.proxy.staticproxy.girlsproxy.Girl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class JsonDemo {

    public static void main(String[] args) {
        List<Girl> list= new LinkedList<Girl>();
        Girl girl = new Girl();
        girl.setName("遇见小星");
        for (int i=0;i<3;i++){
            girl.setAge(i);
            girl.setBirthDay(new Date());
            list.add(girl);
        }
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getBirthDay());
        }

        //时间格式化
        String data = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteDateUseDateFormat);
        JSONArray jsonArray = JSON.parseArray(data);
        System.out.println(jsonArray);
    }
}
