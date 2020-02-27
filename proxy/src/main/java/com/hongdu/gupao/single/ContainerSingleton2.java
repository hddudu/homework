package com.hongdu.gupao.single;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tom.
 */
public class ContainerSingleton2 {

    private ContainerSingleton2(){}

    private static Map<String,Object> ioc = new ConcurrentHashMap<String, Object>();

    public static Object getInstance(String className){
        Object instance = null;
        if(!ioc.containsKey(className)){// 首先还是最外面加一个判断 ： 这个是为了 较少阻塞的线程
            try {
                instance = Class.forName(className).newInstance();
                //然后是不一定要每次都要put ： 因为可能ioc容器已经有实例了 ； 所以在put前再加一个判断
                //在这个之前的判断添加一个锁： 保证put只执行一次
                synchronized (Object.class) {
                    if(!ioc.containsKey(className)) {
                        ioc.put(className, instance);
                    } else {
                        return ioc.get(className);
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return instance;
        }else{
            return ioc.get(className);
        }
    }

}
