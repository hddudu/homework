package com.hongdu.gupao.single;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 */
public class ContainerSingleton {

    private static ContainerSingleton singleton = new ContainerSingleton();

    private ContainerSingleton(){
        if(singleton != null) {
            throw new RuntimeException("对象已经实例化， 不能重复创建！");
        }
    }
    private static Map<String,Object> ioc = new ConcurrentHashMap<String, Object>();
    // 多线程会 访问到这里来的时候， 又会出现覆盖实例的问题
    static volatile Object instance = null;
    public static Object getInstance(String className){

        //这个地方会有线程安全问题：
//        synchronized (singleton) {// 不能加到外面 ： 性能损耗大
        if(instance == null ) {
            synchronized (singleton) {
                if(instance == null) {
                    try {
                        instance = Class.forName(className).newInstance();
                        ioc.put(className, instance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;

//            if(!ioc.containsKey(className)){
//                synchronized (singleton) {
//                    try {
//                        instance = Class.forName(className).newInstance();
//                        ioc.put(className, instance);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                    return instance;
//                }
//            }else{
//                return ioc.get(className);
//            }
//        }
    }

}
