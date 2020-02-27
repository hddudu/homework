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
//    static volatile Object instance = null;
    public static Object getInstance(String className){
//    public synchronized static Object getInstance(String className){
        //方式一 ： 加锁到方法 ： 阻塞严重 : 等待不要重复执行的代码块比较大 ： 花费时间多: 不采用

        //这个地方会有线程安全问题：
//        synchronized (singleton) {// 不能加到外面 ： 性能损耗大
        //这个写法不对 ： 经过一次 CLassName后， Instance一定有值了， 那返回的永远是容器中注册的唯一的一个实例对象
//        if(instance == null ) {
//            synchronized (singleton) {
//                if(instance == null) {
//                    try {
//                        instance = Class.forName(className).newInstance();
//                        ioc.put(className, instance);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return instance;

        Object instance = null;
        //包含大代码块 : 方式二： 仍然感觉不太好 : 比锁整个方法好不了多少
//        synchronized (singleton) {
//            if(!ioc.containsKey(className)){
//                try {
//                    instance = Class.forName(className).newInstance();
//                    ioc.put(className, instance);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                return instance;
//            }else{
//                return ioc.get(className);
//            }
//        }


        if(!ioc.containsKey(className)){
            try {
                instance = Class.forName(className).newInstance();
                //方式三： 感觉稍微好点 ： 还是不是很爽
//                synchronized (singleton) {
//                    if(ioc.containsKey(className)) {
//                        return ioc.get(className);
//                    } else {
//                        ioc.put(className, instance);
//                    }
//                }

                ioc.put(className, instance);

            }catch (Exception e){
                e.printStackTrace();
            }
            return instance;
        }else{
            return ioc.get(className);
        }

    }
}
