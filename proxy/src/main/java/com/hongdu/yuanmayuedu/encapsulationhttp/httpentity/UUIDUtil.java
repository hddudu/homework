package com.hongdu.yuanmayuedu.encapsulationhttp.httpentity;
import org.junit.Test;

import java.util.UUID;

/**
 * @ClassName UUIDUtil
 * @Description 生产uuid
 * @Author dudu
 * @Date 2019/6/26 9:20
 * @Version 1.0
 */
public class UUIDUtil {
    public synchronized static String createUUID(){
        UUID uuid = UUID.randomUUID();
        //去掉中间的分隔
        String uid = uuid.toString().replaceAll("-", "");
        return uid;
    }
    @Test
    public void test() {
        System.out.println(createUUID());
    }
}
