package com.hongdu.hdutil;

import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName HdJavaEveUtils
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/3 18:45
 * @Version 1.0
 */
public class HdJavaEveUtils {

    //遍历map集合 并打印
    public static void printMap(Map<String, String> map) {
        Set<Map.Entry<String, String>> omaps = map.entrySet();
        for (Map.Entry<String, String> o : omaps) {
            System.out.println(o.getKey() + " : " + o.getValue());
        }
    }

    //遍历Files:
    public static void printFiles(File directory) throws Exception {
        if(!directory.exists()) {
            throw new IllegalArgumentException("目录："+directory+"不存在.");
        }
        if(!directory.isDirectory()){
            throw new IllegalArgumentException(directory+"不是目录。");
        }
        File[] dirs = directory.listFiles();
        if(dirs != null && dirs.length > 0) {
            for (File f : dirs) {
                if(f.isDirectory()) {
                    printFiles(f);
                } else {
                    System.out.println(f.getCanonicalPath() + " : " + f.getName());
                }
            }
        }
    }

}
