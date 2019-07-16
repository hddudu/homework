package com.hongdu.spring.utils;

import java.net.URL;

/**
 * @ClassName ScanPackageUtils
 * @Description 包扫描的工具类
 * @Author dudu
 * @Date 2019/7/16 16:27
 * @Version 1.0
 */
public final class ScanPackageUtils {

    /**
     * 初始化单例
     */
    private static volatile ScanPackageUtils instance;

    private ScanPackageUtils() {
    }

    public static ScanPackageUtils getInstance() {
        if(instance == null) {
            synchronized (ScanPackageUtils.class) {
                if(null == instance) {
                    instance = new ScanPackageUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 获取包的文件夹
     * @param packageName
     * @return
     */
    public  URL getUrl(String packageName) {
        return this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
    }

    /**
     * 扫描到一个map : 不行 都是引用传递===>大错特错==》 java都是值传递
     */
}
